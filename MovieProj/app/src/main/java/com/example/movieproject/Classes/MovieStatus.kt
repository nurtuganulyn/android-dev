package com.example.movieproject.MovieClasses

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie_statuses")
data class MovieStatus(
    @PrimaryKey
    @SerializedName("id") val movieId: Int,
    @SerializedName("favorite") val selectedStatus: Boolean
)
