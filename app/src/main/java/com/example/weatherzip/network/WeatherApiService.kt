package com.example.weatherzip.network

import com.example.weatherzip.data.WeatherResponse
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

val gson = GsonBuilder().create()!!
val apiClient = OkHttpClient.Builder().addInterceptor(OpenWeatherInterceptor()).build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(gson))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(apiClient)
    .build()

interface WeatherApiService {
    @GET("weather")
    fun getWeatherAsync(@Query("zip") zipCode: String): Call<WeatherResponse>
}

object WeatherApi {
    val retroFitService: WeatherApiService = retrofit.create(WeatherApiService::class.java)
}