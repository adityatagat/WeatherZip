package com.example.weatherzip.network

import android.util.Log
import com.example.weatherzip.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class OpenWeatherInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url: HttpUrl = chain.request().url().newBuilder()
            .addQueryParameter("apiKey", BuildConfig.OPEN_WEATHER_API_KEY)
            .addQueryParameter("product", "observation")
            .addQueryParameter("metric", "false")
            .build()
        Log.i(" URL ", url.toString())
        return chain.proceed(
            chain.request().newBuilder().addHeader("Accept", "application/json").url(url).build()
        )
    }

}