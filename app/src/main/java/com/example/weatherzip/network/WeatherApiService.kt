package com.example.weatherzip.network

import com.example.weatherzip.data.WeatherResponse
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://weather.ls.hereapi.com/weather/1.0/"

val gson = GsonBuilder().create()!!
val apiClient = OkHttpClient.Builder().addInterceptor(OpenWeatherInterceptor()).build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(gson))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(apiClient)
    .build()

interface WeatherApiService {
    @GET("report.json")
    fun getWeatherAsync(@Query("zipcode") zipCode: String): Deferred<WeatherResponse>
}

object WeatherApi {
    val retroFitService: WeatherApiService = retrofit.create(WeatherApiService::class.java)
}