package com.example.weatherzip.data

import com.google.gson.annotations.SerializedName

data class TempObservation(
    @SerializedName("description") val description: String,
    @SerializedName("temperature") val temperature: String,
    @SerializedName("highTemperature") val highTemperature: String,
    @SerializedName("lowTemperature") val lowTemperature: String,
    @SerializedName("iconLink") val iconLink: String
)