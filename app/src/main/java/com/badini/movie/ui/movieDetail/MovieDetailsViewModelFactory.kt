package com.badini.movie.ui.movieDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.badini.movie.data.repositories.MovieDetailsRespository

class MovieDetailsViewModelFactory (
    private val detailsRespository: MovieDetailsRespository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailViewModel(detailsRespository) as T
    }
}