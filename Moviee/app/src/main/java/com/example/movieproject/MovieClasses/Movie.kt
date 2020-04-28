package com.example.movieproject.MovieClasses

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.kino.MovieClasses.Producers
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class Movie (
    @PrimaryKey
    @SerializedName("id") var id: Int = 0,
    @SerializedName("vote_count") var voteCount: Int = 0,
    @SerializedName("title") var title: String = "",
    @SerializedName("vote_average") var voteAverage: Double = 0.0,
    @SerializedName("poster_path") var posterPath: String = "",
    @SerializedName("release_date") var releaseDate: String = "",
    @SerializedName("popularity") var popularity: String = "",
    @SerializedName("runtime") var runtime: Int = 0,
    @SerializedName("budget") var budget: Int = 0,
    @SerializedName("revenue") var revenue: Int = 0,
    @SerializedName("overview") var overview: String = "",

    @Ignore
    @SerializedName("production_companies") var producers: List<Producers>? = null,
    @Ignore
    @SerializedName("genres") var genres: List<Genre>? = null,
    @Ignore
    var position: Int = 0,
    var isClicked: Boolean = false,
    var genreNames: String = "",
    var producersName: String = ""
)

