package com.badini.movie.ui.main

import androidx.databinding.ObservableField
import com.badini.movie.data.model.api.Movie

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

    }

    fun onItemClick() {
        listener.onItemClick(movie)
    }

    interface MovieItemViewModelListener {
        fun onItemClick(movie: Movie?)
    }
}