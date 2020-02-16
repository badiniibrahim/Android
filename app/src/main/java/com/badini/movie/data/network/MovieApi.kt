package com.badini.movie.data.network

import android.util.Log
import com.badini.movie.BuildConfig
import com.badini.movie.data.model.api.MovieDetails
import com.badini.movie.data.model.api.MovieResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface MovieApi {


    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") id: Int,
        @Query("language") language: String
    ) : Response<MovieDetails>

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("page") page: Int,
        @Query("language") language: String
    ) : Response<MovieResponse>



    companion object{
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor,
            apiKeyInterceptor: ApiKeyInterceptor
        ): MovieApi{

            val okkHttpclient = OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(networkConnectionInterceptor)
                .retryOnConnectionFailure(true)
                .addInterceptor(apiKeyInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieApi::class.java)
        }
    }
}