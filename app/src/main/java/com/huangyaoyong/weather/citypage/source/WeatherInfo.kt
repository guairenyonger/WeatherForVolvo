package com.huangyaoyong.weather.citypage.source

/**
 * @Author: huangyaoyong
 * @datetime: 2023/6/22
 * @desc:
 */
data class WeatherInfo (
    val count: String,
    val info: String,
    val infocode: String,
    val lives: List<Live>?,
    val forecasts: List<Forecast>?,
    val status: String
)

data class Live(
    val adcode: String,
    val city: String,
    val humidity: String,
    val humidity_float: String,
    val province: String,
    val reporttime: String,
    val temperature: String,
    val temperature_float: String,
    val weather: String,
    val winddirection: String,
    val windpower: String
)

data class Forecast(
    val city: String,
    val adcode: String,
    val province: String,
    val reporttime: String,
    val casts: List<Cast>?
    )

data class Cast(
    val date: String,
    val week: String,
    val dayweather: String,
    val nightweather: String,
    val daytemp: String,
    val nighttemp: String,
    val daywind: String,
    val nightwind: String,
    val daypower: String,
    val nightpower: String,
)