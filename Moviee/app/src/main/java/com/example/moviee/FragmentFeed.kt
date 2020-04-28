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
import com.example.movieproject.MovieClasses.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import kotlin.coroutines.CoroutineContext


class FragmentFeed: Fragment(), MovieAdapter.RvItemClickListener, CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

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
            try {
                val response = ServiceBuilder.getPostApi().getPopularMoviesList(MovieDBApiKey)
                movieList = mutableListOf()
                if (response.isSuccessful) {
                    val movies = response.body()
                    if (movies != null) {
                        for (movie: Movie in movies.movieList) {
                            likeStatus(movie)
                            movieList.add(movie)
                        }
                    }
                    adapter?.movies = movieList
                    adapter?.notifyDataSetChanged()

                }
                swipeRefreshLayout.isRefreshing = false
            }
            catch (e: Exception) {
                swipeRefreshLayout.isRefreshing = false
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
        launch {
            try {
                val response = ServiceBuilder.getPostApi().addRemoveFavourites(MovieDBApiKey, sessionId, likedMovie)
                if (response.isSuccessful) {

                }
            } catch (e:Exception) {

            }
        }
    }


     fun likeStatus(movie: Movie){
        launch {
            try {
                val response = ServiceBuilder.getPostApi().getMovieStates(movie.id, MovieDBApiKey, sessionId)
                if (response.isSuccessful) {
                    val movieStatus = response.body()
                    if (movieStatus != null) {
                        movie.isClicked = movieStatus.selectedStatus
                        adapter?.notifyDataSetChanged()
                    }
                }
            } catch (e:Exception) {

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}
