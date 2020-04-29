package com.example.movieproject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.kino.MovieStatusDao
import com.example.movieproject.Database.MovieDao
import com.example.movieproject.Database.MovieDatabase
import com.example.movieproject.MovieClasses.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import kotlin.coroutines.CoroutineContext


class FavouritesFragment: Fragment(), MovieAdapter.RvItemClickListener, CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private var movieDao: MovieDao? = null
    private var movieStatusDao: MovieStatusDao? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private var adapter: MovieAdapter? = null
    private var sessionId: String = ""
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recycle_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieDao = MovieDatabase.getDatabase(context = requireActivity()).movieDao()
        movieStatusDao = MovieDatabase.getDatabase(context = requireContext()).movieStatusDao()

        sharedPreferences = requireActivity().getSharedPreferences(
            getString(R.string.preference_file), Context.MODE_PRIVATE
        )

        if (sharedPreferences.contains(getString(R.string.session_id))) {
            sessionId =
                sharedPreferences.getString(getString(R.string.session_id), "null") as String
        }

        bindViews(view)

        initAdapter()

        getMovies()

    }

    private fun initAdapter() {
        adapter =
            this.context?.let { MovieAdapter(itemClickListener = this) }
        recyclerView.adapter = adapter
    }

    private fun bindViews(view: View) = with(view) {
        recyclerView = findViewById(R.id.like_fragment)
        recyclerView.layoutManager = LinearLayoutManager(context)
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)

        swipeRefreshLayout.setOnRefreshListener {
            adapter?.clearAll()
            getMovies()
        }
    }

    override fun itemClick(position: Int, movie: Movie) {
        val intent = Intent(context, SingleMovieActivity::class.java)
        intent.putExtra("movie_id", movie.id)
        startActivity(intent)
    }

    private fun getMovies() {
        launch {
            swipeRefreshLayout.isRefreshing = true
            withContext(Dispatchers.IO) {
                refreshFavourites()
            }
            val favMovies = withContext(Dispatchers.IO) {
                try {
                    delay(500)
                    val response = ServiceBuilder.getPostApi().
                        getFavouriteMovies(MovieDBApiKey, sessionId)
                    if (response.isSuccessful) {
                        val movies = response.body()?.movieList
                        if (!movies.isNullOrEmpty()) {
                            for (movie in movies) {
                                movie.isClicked = true
                            }
                        }
                        return@withContext movies
                    } else {
                        return@withContext movieDao?.getFavouriteMovies() ?: emptyList()
                    }
                } catch (e:Exception) {
                    return@withContext movieDao?.getFavouriteMovies() ?: emptyList()
                }
            }
            swipeRefreshLayout.isRefreshing = false
            if (favMovies != null) {
                adapter?.changeMovies(favMovies)
            }
        }
    }

    private fun refreshFavourites() {
        val movies = movieStatusDao?.getMovieStatuses()
        if (!movies.isNullOrEmpty()) {
            for (movie in movies) {
                val likedMovie = LikedMovie(movieId = movie.movieId,
                    selectedStatus = movie.selectedStatus)
                addRemoveFavourites(likedMovie)
            }
        }
        movieStatusDao?.deleteAll()
    }


    private fun addRemoveFavourites(likedMovie: LikedMovie) {
        launch {
            try {
                val response = ServiceBuilder.getPostApi().addRemoveFavourites(MovieDBApiKey, sessionId, likedMovie)
                if (response.isSuccessful) {
                }
            } catch (e:Exception) {
                withContext(Dispatchers.IO) {
                    movieDao?.updateMovieIsCLicked(
                        likedMovie.selectedStatus,
                        likedMovie.movieId
                    )
                    val moviesStatus = MovieStatus(likedMovie.movieId, likedMovie.selectedStatus)
                    movieStatusDao?.insertMovieStatus(moviesStatus)
                }
            }
        }
    }

    override fun addToFavourites(position: Int, item: Movie) {
        lateinit var likedMovie: LikedMovie

        if (!item.isClicked) {
            item.isClicked = true
            likedMovie = LikedMovie("movie", item.id, item.isClicked)
        } else {
            item.isClicked = false
            likedMovie = LikedMovie("movie", item.id, item.isClicked)
            likedMovie.selectedStatus = item.isClicked
        }
        addRemoveFavourites(likedMovie)
        swipeRefreshLayout.isRefreshing = true
        adapter?.clearAll()
        getMovies()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}