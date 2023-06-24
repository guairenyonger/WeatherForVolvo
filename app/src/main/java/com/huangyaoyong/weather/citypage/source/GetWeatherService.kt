package com.huangyaoyong.weather.citypage.source

import retrofit2.http.GET
import retrofit2.http.Query

interface GetWeatherService {

    @GET("v3/weather/weatherInfo")
    suspend fun getWeather(
        @Query("city") city: String,
        @Query("extensions") extensions: String = "all",
        @Query("key") key: String = "69b8d7de510addc85b45046efe2a74db"/*实战中应该在CI中注入key*/
    ): WeatherInfo
}