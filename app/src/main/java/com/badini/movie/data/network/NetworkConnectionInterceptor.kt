package com.badini.movie.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.badini.movie.R
import com.badini.movie.utils.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(
    private val context: Context
) : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable())
            throw NoInternetException(context.getString(R.string.internet_avaible_message))
        return chain.proceed(chain.request())
    }

    private val applicationContext = context.applicationContext

    private fun isInternetAvailable(): Boolean {
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }

    }
}