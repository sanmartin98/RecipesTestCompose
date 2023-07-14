package com.example.data.remote.response

import com.google.gson.annotations.SerializedName

data class LocationResponseDTO(
    @SerializedName("lat")
    val lat: String,
    @SerializedName("lng")
    val lng: String,
    @SerializedName("location_name")
    val locationName: String
)
