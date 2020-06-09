package com.example.weatherzip.data

import com.google.gson.annotations.SerializedName

data class Observations(
    @SerializedName("location") val location: List<Location>
)
