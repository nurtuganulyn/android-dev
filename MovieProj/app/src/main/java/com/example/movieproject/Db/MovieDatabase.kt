package com.example.movieproject.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kino.MovieStatusDao
import com.example.movieproject.MovieClasses.Movie
import com.example.movieproject.MovieClasses.MovieStatus

@Database(entities = [Movie::class, MovieStatus::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun movieStatusDao() : MovieStatusDao

    companion object {
        var database: MovieDatabase? = null
        private const val databaseName: String = "app.database.db"
        fun getDatabase(context: Context): MovieDatabase{
            if (database == null) {
                database = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    databaseName
                ).build()
            }
            return database!!
        }
    }
}