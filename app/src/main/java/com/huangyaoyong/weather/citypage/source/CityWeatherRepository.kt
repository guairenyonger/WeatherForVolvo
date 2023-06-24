package com.huangyaoyong.weather.citypage.source

import com.huangyaoyong.weather.base.ServiceCreater
import com.huangyaoyong.weather.base.UIState
import com.huangyaoyong.weather.citypage.WeatherItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * @Author: huangyaoyong
 * @datetime: 2023/6/24
 * @desc:
 */
class CityWeatherRepository {

    fun getCityWeather(code: String) = flow {
        val weatherInfo = ServiceCreater.create(GetWeatherService::class.java).getWeather(code)

        val uiList = mutableListOf<WeatherItem>()

        weatherInfo.forecasts!![0].casts?.takeIf { it.isNotEmpty() }
            ?.forEachIndexed { index, cast ->
                if (index == 0) {
                    uiList.add(WeatherItem.createTodayItem(cast))
                } else {
                    uiList.add(WeatherItem.createForecastItem(cast))
                }
            }

        emit(UIState.onSuccess(uiList))
    }.flowOn(Dispatchers.IO)
        .catch {
            emit(UIState.onError(it))
        }
}