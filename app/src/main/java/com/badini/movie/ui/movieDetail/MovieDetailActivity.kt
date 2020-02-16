package com.badini.movie.ui.movieDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.badini.movie.R
import com.badini.movie.data.model.api.MovieDetails
import com.badini.movie.data.model.db.Favoris
import com.badini.movie.databinding.ActivityMovieDetailBinding
import com.badini.movie.utils.Constante.Companion.POSTER_BASE_URL
import com.badini.movie.utils.snackbar
import com.bumptech.glide.Glide
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.text.NumberFormat
import java.util.*

class MovieDetailActivity : AppCompatActivity(), NavigatorDetails, KodeinAware {

    override val kodein by kodein()

    var detailViewModel: MovieDetailViewModel? = null
    var activityMovieDetailBinding: ActivityMovieDetailBinding? = null
    private val mfactory : MovieDetailsViewModelFactory by instance()
    var movieDetails: MovieDetails? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMovieDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)
        detailViewModel = ViewModelProviders.of(this, mfactory).get(MovieDetailViewModel::class.java)
        activityMovieDetailBinding!!.detailViewModel = detailViewModel
        detailViewModel!!.setNavigator(this)
        subscribeToLiveData()

    }

    override fun onStart() {
        super.onStart()
        detailViewModel!!.getMovieDetails(intent.getIntExtra("id", 1))
    }


    fun subscribeToLiveData(){
        detailViewModel!!.getMovieDeatailLiveData().observe(this, Observer {
            movieDetails = it
            activityMovieDetailBinding!!.movieTitle.text = it.title
            activityMovieDetailBinding!!.movieTagline.text = it.tagline
            activityMovieDetailBinding!!.movieReleaseDate.text = it.releaseDate
            activityMovieDetailBinding!!.movieRating.text = it.rating.toString()
            activityMovieDetailBinding!!.movieRuntime.text = it.runtime.toString() + " minutes"
            activityMovieDetailBinding!!.movieOverview.text = it.overview

            val formatCurrency = NumberFormat.getCurrencyInstance(Locale.FRANCE)
            activityMovieDetailBinding!!.movieBudget.text = formatCurrency.format(it.budget)
            activityMovieDetailBinding!!.movieRevenue.text = formatCurrency.format(it.revenue)

            val moviePosterURL = POSTER_BASE_URL + it.posterPath
            Glide.with(this)
                .load(moviePosterURL)
                .into(activityMovieDetailBinding!!.ivMoviePoster);

        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.favoris_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.ic_favoris -> {
                if (movieDetails != null){
                    val favoris = Favoris(
                        movieDetails!!.id,
                        movieDetails!!.title,
                        movieDetails!!.posterPath,
                        movieDetails!!.releaseDate
                    )
                    detailViewModel?.saveFavoris(favoris)
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun success(success: Boolean) {
        if (success){
            activityMovieDetailBinding!!.detailLayout.snackbar(getString(R.string.success_message))
        }
    }

    override fun failed(failure: Boolean) {
        if (failure){
            activityMovieDetailBinding!!.detailLayout.snackbar(getString(R.string.failed_message))

        }
    }

}
