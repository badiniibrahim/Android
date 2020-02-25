package com.badini

import android.app.Application
import com.badini.movie.data.local.db.AppDatabase
import com.badini.movie.data.network.ApiKeyInterceptor
import com.badini.movie.data.network.MovieApi
import com.badini.movie.data.network.NetworkConnectionInterceptor
import com.badini.movie.data.repositories.FavorisRepository
import com.badini.movie.data.repositories.MovieDataSourceFactory
import com.badini.movie.data.repositories.MovieDataSourceRepository
import com.badini.movie.data.repositories.MovieDetailsRespository
import com.badini.movie.ui.favoris.FavorisViewModelFactory
import com.badini.movie.ui.main.MainViewModelFactory
import com.badini.movie.ui.movieDetail.MovieDetailsViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MovieApp: Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MovieApp))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { ApiKeyInterceptor(instance()) }
        bind() from singleton { MovieApi(instance(),instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { FavorisRepository(instance()) }
        bind() from singleton { MovieDetailsRespository(instance(), instance()) }
        bind() from singleton { MovieDataSourceFactory(instance()) }
        bind() from singleton { MainViewModelFactory(instance()) }
        bind() from singleton { MovieDataSourceRepository(instance()) }
        bind() from provider  { MovieDetailsViewModelFactory(instance()) }
        bind() from provider { FavorisViewModelFactory(instance()) }

    }
}