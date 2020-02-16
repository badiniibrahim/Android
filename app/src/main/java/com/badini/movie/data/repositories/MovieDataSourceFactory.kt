package com.badini.movie.data.repositories

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.badini.movie.data.model.api.Movie
import com.badini.movie.data.network.MovieApi

class MovieDataSourceFactory(
    private val api: MovieApi
) : DataSource.Factory<Int, Movie>() {

    val movieDataSourceLiveData = MutableLiveData<MovieDataSourceRepository>()

    override fun create(): DataSource<Int, Movie> {
        val movieDataSource = MovieDataSourceRepository(api)
        movieDataSourceLiveData.postValue(movieDataSource)
        return movieDataSource
    }
}