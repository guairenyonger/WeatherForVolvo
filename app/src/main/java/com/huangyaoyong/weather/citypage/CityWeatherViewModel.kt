package com.huangyaoyong.weather.citypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huangyaoyong.weather.base.UIState
import com.huangyaoyong.weather.citypage.source.CityWeatherRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * @Author: huangyaoyong
 * @datetime: 2023/6/22
 * @desc:
 */
class CityWeatherViewModel(private val repository: CityWeatherRepository = CityWeatherRepository()) :
    ViewModel() {

    private val _intent = MutableSharedFlow<String>()

    val uiState = _intent.flatMapLatest {
        repository.getCityWeather(it)
    }.stateIn(viewModelScope, SharingStarted.Lazily, UIState.Loading())

    fun show(code: String) {
        viewModelScope.launch {
            _intent.emit(code)
        }
    }
}