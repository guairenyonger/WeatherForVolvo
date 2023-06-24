package com.huangyaoyong.weather.base

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @Author: huangyaoyong
 * @datetime: 2023/6/22
 * @desc:
 */
object ServiceCreater {
    private const val BASE_URL = "https://restapi.amap.com"
    private const val CONNECT_TIMEOUT = 30L
    private const val READ_TIMEOUT = 10L
    private val retrofit: Retrofit by lazy {
        create()
    }

    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }

    private fun create(): Retrofit {
        val okHttpClientBuilder = OkHttpClient().newBuilder().apply {
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        }
        return Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            client(okHttpClientBuilder.build())
            addConverterFactory(GsonConverterFactory.create())
        }.build()
    }
}