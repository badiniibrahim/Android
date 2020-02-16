package com.badini.movie.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.badini.movie.data.model.api.Movie
import com.badini.movie.data.network.MovieApi
import com.badini.movie.data.network.NetWorkState
import com.badini.movie.data.repositories.MovieDataSourceFactory
import com.badini.movie.data.repositories.MovieDataSourceRepository
import com.badini.movie.ui.base.BaseViewModel


class MainViewModel(
     private val api: MovieApi
): BaseViewModel<MainNavigator>() {


    var postsLiveData: LiveData<PagedList<Movie>>
    private val movieDataSourceFactory: MovieDataSourceFactory

    init {
        movieDataSourceFactory = MovieDataSourceFactory(api)
        val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setEnablePlaceholders(false)
            .build()
        postsLiveData  = LivePagedListBuilder<Int, Movie>(movieDataSourceFactory, config).build()

    }

    fun getPosts():LiveData<PagedList<Movie>> = postsLiveData

    fun getNetworkState(): LiveData<NetWorkState> = Transformations.switchMap<MovieDataSourceRepository,
            NetWorkState>(movieDataSourceFactory.movieDataSourceLiveData, MovieDataSourceRepository::netWorkState)

}