package com.example.movieproject.MovieClasses

import com.example.kino.MovieClasses.Producers
import com.google.gson.annotations.SerializedName

data class SingleMovie(
    @SerializedName("id") val id: Int,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("title") val title: String,
    @SerializedName("vote_average") val voteAverage: Float,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("runtime") val runtime: Int,
    @SerializedName("genres") val genres: List<Genre>,
    @SerializedName("production_companies") val producers: List<Producers>,

    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("budget") val budget: Int,
    @SerializedName("revenue") val revenue: Int,
    @SerializedName("overview") val overview: String
)