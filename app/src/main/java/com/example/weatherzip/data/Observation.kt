package com.example.weatherzip.data

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("observation") val tempObservations: List<TempObservation>,
    @SerializedName("state") val state: String,
    @SerializedName("city") val city: String
)