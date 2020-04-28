package com.example.movieproject.Database

import androidx.room.*
import com.example.movieproject.MovieClasses.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<Movie>)

    @Update
    fun updateMovie(movies: Movie)

    @Query("SELECT * FROM movies ORDER BY voteAverage DESC, voteCount DESC")
    fun getMovies(): List<Movie>

    @Query("SELECT * FROM movies WHERE isClicked=1")
    fun getFavouriteMovies(): List<Movie>

    @Query("SELECT * FROM movies WHERE id=:id")
    fun getMovie(id: Int): Movie

    @Query("UPDATE movies SET runtime = :runtime WHERE id = :id")
    fun updateMovieRuntime(runtime: Int, id: Int)

    @Query("UPDATE movies SET isClicked = :isClicked WHERE id = :id")
    fun updateMovieIsCLicked(isClicked: Boolean, id: Int)

    @Query("DELETE FROM movies")
    fun deleteAll()
}