package com.badini.movie.ui.main

import androidx.databinding.ObservableField
import com.badini.movie.data.model.api.Movie
import com.badini.movie.utils.Constante
import com.bumptech.glide.Glide

class MovieItemViewModel(
    private val movie: Movie,
    private val listener: MovieItemViewModelListener
) {
    var title: ObservableField<String>? = null
    var releaseDate: ObservableField<String>? = null
    var mov: Movie? = null
    var mListener: MovieItemViewModelListener? = null

    init {
        this.mov = movie
        this.mListener = listener
        title = ObservableField(movie.title)
        releaseDate = ObservableField(movie.releaseDate)

        /*val moviePosterURL = Constante.POSTER_BASE_URL + it.posterPath
        Glide.with(this)
            .load(moviePosterURL)
            .into(activityMovieDetailBinding!!.ivMoviePoster);*/

    }

    fun onItemClick() {
        listener.onItemClick(movie)
    }

    interface MovieItemViewModelListener {
        fun onItemClick(movie: Movie?)
    }
}