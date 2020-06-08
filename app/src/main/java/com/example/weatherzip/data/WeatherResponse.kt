package com.example.weatherzip.data

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("name") var cityName: String,
    @SerializedName("weather") var weather: List<WeatherDescription>,
    @SerializedName("main") var temperature: Temperature,
    @SerializedName("dt") var date: Long
)
