package com.badini.movie.ui.favoris

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.badini.movie.data.repositories.FavorisRepository

class FavorisViewModelFactory(
    private val favorisRepository: FavorisRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavorisViewModel(favorisRepository) as T
    }
}