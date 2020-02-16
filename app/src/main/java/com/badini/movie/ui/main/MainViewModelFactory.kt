package com.badini.movie.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.badini.movie.data.network.MovieApi

class MainViewModelFactory (
    private val api: MovieApi
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(api) as T
    }
}