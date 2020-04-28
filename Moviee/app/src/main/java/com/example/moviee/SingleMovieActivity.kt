package com.example.movieproject

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.movieproject.MovieClasses.SingleMovie
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class SingleMovieActivity : AppCompatActivity() , CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


    private lateinit var progressBar: ProgressBar
    private lateinit var backBtn: ImageButton
    private lateinit var poster: ImageView
    private lateinit var posterFull: ImageView
    private lateinit var title: TextView
    private lateinit var releaseYear: TextView
    private lateinit var releaseDate: TextView
    private lateinit var duration: TextView
    private lateinit var plot: TextView
    private lateinit var rating: TextView
    private lateinit var budget: TextView
    private lateinit var revenue: TextView
    private lateinit var genres: TextView
    private lateinit var producers: TextView
    private lateinit var mainLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_movie);

        mainLayout = findViewById(R.id.mainLayout)
        mainLayout.visibility = View.INVISIBLE;

        val movieID = intent.getIntExtra("movie_id", 1);

        progressBar = findViewById(R.id.progressBar)
        backBtn = findViewById(R.id.btnBack)
        poster = findViewById(R.id.ivBanner)
        title = findViewById(R.id.tvMovieTitle)
        releaseYear = findViewById(R.id.tvReleaseYear)
        releaseDate = findViewById(R.id.tvReleaseDate)
        posterFull = findViewById(R.id.ivFull)
        duration = findViewById(R.id.tvDuration)
        plot = findViewById(R.id.tvPlot)
        rating = findViewById(R.id.tvRating)
        budget = findViewById(R.id.tvBudget)
        revenue = findViewById(R.id.tvRevenue)
        genres = findViewById(R.id.tvGenre)
        producers = findViewById(R.id.tvProducers)

        backBtn.setOnClickListener{
            finish();
        }
        getMovie(movieID)

    }

    private fun getMovie(id: Int) {
        launch {
            try {
                val response = ServiceBuilder.getPostApi().getMovie(id, MovieDBApiKey)
                if (response.isSuccessful) {
                    val singleMovie = response.body()
                    if (singleMovie != null) {
                        mainLayout.visibility = View.VISIBLE
                        title.text = singleMovie.title
                        releaseYear.text = singleMovie.releaseDate.substring(0,4)
                        releaseDate.text = singleMovie.releaseDate
                        duration.text = "${singleMovie.runtime.toString()} ${getString(R.string.min)}"
                        plot.text = singleMovie.overview
                        rating.text = singleMovie.voteAverage.toString()
                        budget.text = "${singleMovie.budget.toString()} ${getString(R.string.dollar)}"
                        revenue.text = "${singleMovie.revenue.toString()} ${getString(R.string.dollar)}"
                        genres.text = ""
                        producers.text = ""

                        for (i in singleMovie.genres.indices) {
                            if (i == 0) {
                                genres.append(singleMovie.genres[i].name.toString());
                            } else {
                                genres.append(", " + singleMovie.genres[i].name.toString());
                            }
                            if (i == 4) break;
                        }

                        for (i in singleMovie.producers.indices) {
                            if (i == 0) {
                                producers.append(singleMovie.producers[i].name.toString());
                            } else {
                                producers.append(", " + singleMovie.producers[i].name.toString());
                            }
                        }


                        Picasso.get()
                            .load("https://image.tmdb.org/t/p/w500" + singleMovie.posterPath)
                            .into(poster)
                        Picasso.get()
                            .load("https://image.tmdb.org/t/p/w500" + singleMovie.posterPath)
                            .into(posterFull)
                    }
                }
                progressBar.visibility = View.GONE
            } catch (e: Exception) {
                progressBar.visibility = View.GONE
            }
        }
    }

}
