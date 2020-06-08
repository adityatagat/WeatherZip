package com.example.weatherzip.data

import com.google.gson.annotations.SerializedName

data class Temperature(
    @SerializedName("temp") var dayTemperature: Double,
    @SerializedName("feels_like") var feelsLikeTemperature: Double,
    @SerializedName("temp_min") var minTemperature: Double,
    @SerializedName("temp_max") var maxTemperature: Double
)
