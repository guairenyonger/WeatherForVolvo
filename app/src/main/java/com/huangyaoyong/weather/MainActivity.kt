package com.huangyaoyong.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.google.android.material.tabs.TabLayoutMediator
import com.huangyaoyong.weather.databinding.ActivityMainBinding
import com.huangyaoyong.weather.home.CityInfo
import com.huangyaoyong.weather.home.PagerAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))

        setContentView(binding.root)

        initCity()
    }

    private fun initCity(){
        val pagerAdapter = PagerAdapter(this)
        binding.vp.adapter = pagerAdapter

        loadTestData().let {
            binding.vp.offscreenPageLimit = it.size
            pagerAdapter.resetDataAndNotify(it)
        }

        TabLayoutMediator(binding.tl, binding.vp) { tab, position ->
            tab.text = pagerAdapter.getTabTitle(position)
        }.attach()
    }

    /**
     * 测试数据
     */
    private fun loadTestData():List<CityInfo>{
        return listOf<CityInfo>(
            CityInfo("110000", "北京"),
            CityInfo("310000", "上海"),
            CityInfo("440100", "广州"),
            CityInfo("440300", "深圳"),
            CityInfo("320500", "苏州"),
            CityInfo("210100", "沈阳")
        )
    }
}