package com.badini.movie.ui.movieDetail

import android.content.ContentUris
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.badini.movie.data.model.api.MovieDetails
import com.badini.movie.data.model.db.Favoris
import com.badini.movie.data.repositories.MovieDetailsRespository
import com.badini.movie.ui.base.BaseViewModel
import com.badini.movie.utils.ApiException
import com.badini.movie.utils.Coroutines
import com.badini.movie.utils.NoInternetException
import java.lang.Exception

class MovieDetailViewModel(
    private val detailsRespository: MovieDetailsRespository
):BaseViewModel<NavigatorDetails>() {

    var movieDetailsistLiveData: MutableLiveData<MovieDetails>
    var movieDetailsObservableField: ObservableField<MovieDetails> = ObservableField()


    init {
        movieDetailsistLiveData = MutableLiveData<MovieDetails>()
    }

    fun getMovieDetails(movieId: Int){
        Coroutines.main{
            setIsLoading(true)
            try {
                val detailresponse = detailsRespository.getMovieDetails(movieId, "fr-FR")
                detailresponse.body()!!.let {
                    movieDetailsistLiveData.value = it
                    setIsLoading(false)
                    return@main
                }

            }catch (e: ApiException){
                setIsLoading(false)
            }catch (e: NoInternetException){
                setIsLoading(false)
            }
        }
    }

    fun additem(moviedetail: MovieDetails){
        movieDetailsObservableField.set(moviedetail)
    }

    fun getMovieDeatailLiveData(): MutableLiveData<MovieDetails> {
        return movieDetailsistLiveData
    }

    fun saveFavoris(favoris: Favoris){
        Coroutines.main {
            try {
                detailsRespository.saveFavoris(favoris)
                getNavigator()?.success(true)
            }catch (e: Exception){
                getNavigator()?.failed(true)
                Log.e("TAG", "Erreur lors de l'insertion")
            }
        }
    }


}