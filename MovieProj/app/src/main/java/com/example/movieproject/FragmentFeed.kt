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
import kotlinx.android.synthetic.main.moviedb_feed.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import kotlin.coroutines.CoroutineContext


class FragmentFeed: Fragment(), MovieAdapter.RvItemClickListener, CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private var movieDao: MovieDao? = null
    private var movieStatusDao: MovieStatusDao? = null

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private  lateinit var recyclerView: RecyclerView
    private lateinit var sharedPreferences: SharedPreferences
    private  var adapter: MovieAdapter? = null
    private  lateinit var sessionId: String
    private lateinit var movieList: MutableList<Movie>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      return inflater.inflate(R.layout.fragment_feed,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieDao = MovieDatabase.getDatabase(context = requireActivity()).movieDao()
        movieStatusDao = MovieDatabase.getDatabase(context = requireActivity()).movieStatusDao()

        sharedPreferences = requireActivity().getSharedPreferences(
           getString(R.string.preference_file), Context.MODE_PRIVATE
        )

        if (sharedPreferences.contains(getString(R.string.session_id))) {
            sessionId = sharedPreferences.getString(getString(R.string.session_id), "null") as String
        }

        bindViews(view)
        initAdapter()

        swipeRefreshLayout.setOnRefreshListener {
            adapter?.clearAll()
            getMovies()
        }
        getMovies()

    }
    private fun initAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter =
            this.context.let { MovieAdapter(itemClickListener = this) }
        recyclerView.adapter = adapter
    }
    private fun bindViews(view: View) = with(view) {
        recyclerView = findViewById(R.id.recy_feed)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
    }

    override fun itemClick(position: Int, movie: Movie) {
        val intent = Intent(context, SingleMovieActivity::class.java)
        intent.putExtra("movie_id", movie.id)
        startActivity(intent)
    }

    private fun getMovies() {
        launch {
            swipeRefreshLayout.isRefreshing = true
            val movies = withContext(Dispatchers.IO) {
                try {
                    refreshFavourites()
                    val response = ServiceBuilder.getPostApi().getPopularMoviesList(MovieDBApiKey)
                    if (response.isSuccessful) {
                        val movies = response.body()?.movieList
                        if (movies != null) {
                            for (movie: Movie in movies) {
                                likeStatus(movie)
                            }
                        }
                        if (!movies.isNullOrEmpty()) {
                            movieDao?.deleteAll()
                            movieDao?.insertAll(movies)
                        }
                        return@withContext movies
                    } else {
                        return@withContext movieDao?.getMovies() ?: emptyList()
                    }
                } catch (e:Exception) {
                    return@withContext movieDao?.getMovies() ?: emptyList()
                }
            }
            swipeRefreshLayout.isRefreshing = false
            if (movies != null) {
                adapter?.changeMovies(movies)
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
    }


     private fun likeStatus(movie: Movie){
        launch {
            try {
                val response = ServiceBuilder.getPostApi().getMovieStates(movie.id, MovieDBApiKey, sessionId)
                if (response.isSuccessful) {
                    val movieStatus = response.body()
                    if (movieStatus != null) {
                        movie.isClicked = movieStatus.selectedStatus
                        withContext(Dispatchers.IO) {
                            movieDao?.updateMovieIsCLicked(movie.isClicked, movie.id)
                        }
                        adapter?.notifyDataSetChanged()
                    }
                }
            } catch (e:Exception) {

            }
        }
    }

    private fun setGenreNames(movie: Movie) {
        movie.genreNames = ""
        var i: Int = 0;
        if (movie.genres.isNullOrEmpty())
        else {
            for (genre in movie.genres!!) {
                movie.genreNames+= "${genre.name}, "
                i+=1;
                if (i == movie.genres!!.size - 1) movie.genreNames+= "${genre.name}"
            }
        }
    }
    private fun setProdNames(movie: Movie) {
        movie.producersName = ""
        var i: Int = 0;
        if (movie.producers.isNullOrEmpty())
        else {
            for (prod in movie.producers!!) {
                movie.producersName+= "${prod.name}, "
                i+=1;
                if (i == movie.producers!!.size - 1) movie.producersName+= "${prod.name}"
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}
