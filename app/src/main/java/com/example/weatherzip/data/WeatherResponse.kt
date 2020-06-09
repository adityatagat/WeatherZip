package com.example.weatherzip.data

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("observations") val Observations: Observations,
    @SerializedName("feedCreation") val lastUpdatedDate: String
)
