package com.huangyaoyong.weather.citypage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.huangyaoyong.weather.base.UIState
import com.huangyaoyong.weather.databinding.FragmentCityWeatherBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * @Author: huangyaoyong
 * @datetime: 2023/6/22
 * @desc:
 */
const val ARGUMENT_CODE = "code"

class CitiWeatherFragment : Fragment() {
    private lateinit var binding: FragmentCityWeatherBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[CityWeatherViewModel::class.java]
    }
    private lateinit var code:String
    private val adapter: WeatherListAdapter by lazy {
        WeatherListAdapter()
    }


    companion object {
        fun newInstance(code: String) =
            CitiWeatherFragment().apply {
                arguments = Bundle().apply { putString(ARGUMENT_CODE, code) }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        code = arguments?.getString(ARGUMENT_CODE) ?: ""

        binding = FragmentCityWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rl.setEnableLoadMore(false)
        binding.rl.setOnRefreshListener { viewModel.show(code) }

        binding.rv.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.uiState.collect {
                    binding.rl.finishRefresh()
                    binding.rl.finishLoadMore()
                    when (it) {
                        is UIState.Success -> {
                            adapter.submitList(it.data)
                        }
                        is UIState.Error -> {
                            Toast.makeText(requireContext(), "请求异常，下拉刷新以重试",Toast.LENGTH_LONG).show()
                        }
                        is UIState.Loading -> {
                            binding.rl.autoRefresh()
                        }
                    }
                }
            }
        }
    }
}