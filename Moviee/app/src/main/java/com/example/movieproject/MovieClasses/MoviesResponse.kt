package com.example.movieproject.MovieClasses
import com.google.gson.annotations.SerializedName

class MoviesResponse (
    @SerializedName("results") val movieList: List<Movie>
)