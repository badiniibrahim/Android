package com.badini.movie.data.repositories

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.badini.movie.data.model.api.Movie
import com.badini.movie.data.network.MovieApi
import com.badini.movie.data.network.NetWorkState
import com.badini.movie.utils.Coroutines

class MovieDataSourceRepository(
    private val api: MovieApi
): PageKeyedDataSource<Int, Movie>() {

    var page = 1
    val netWorkState: MutableLiveData<NetWorkState> = MutableLiveData()
    val language: String = "fr-FR"

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        netWorkState.postValue(NetWorkState.LOADING)
        Coroutines.main {
            api.getPopularMovie(page, language).let {
                callback.onResult(it.body()!!.movies, null, page+1)
                netWorkState.postValue(NetWorkState.DONE)
                return@main
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        netWorkState.postValue(NetWorkState.LOADING)
        Coroutines.main {
            api.getPopularMovie(params.key, language).let {
                if (it.body()!!.totalPages >= params.key){
                    callback.onResult(it.body()!!.movies,  params.key+1)
                    netWorkState.postValue(NetWorkState.DONE)
                    return@main
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

    }

}