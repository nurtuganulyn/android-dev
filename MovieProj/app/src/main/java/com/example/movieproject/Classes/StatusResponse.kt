package com.example.movieproject.MovieClasses

import com.google.gson.annotations.SerializedName

data class StatusResponse (@SerializedName("status_message") val statusMessage: String
)