package com.badini.movie.data.repositories

import com.badini.movie.data.local.db.AppDatabase
import com.badini.movie.data.model.api.MovieDetails
import com.badini.movie.data.model.db.Favoris
import com.badini.movie.data.network.MovieApi
import com.badini.movie.data.network.SafeApiRequest
import retrofit2.Response

class MovieDetailsRespository (
    private val api: MovieApi,
    private val db: AppDatabase
    ): SafeApiRequest(){


    suspend fun getMovieDetails(movieId: Int, language: String) : Response<MovieDetails>{
        return apiRequest{ api.getMovieDetails(movieId, language)}
    }

    suspend fun saveFavoris(favoris: Favoris) = db.getfavorisDao().insertOrUpdate(favoris)

    suspend fun getAllFavoris(): List<Favoris>{
        return db.getfavorisDao().loadAllFavoris()
    }

}