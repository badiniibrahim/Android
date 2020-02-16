package com.badini.movie.data.network

import android.content.Context
import android.util.Log
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class ApiKeyInterceptor (
    private val context: Context
): Interceptor{

    val API_KEY: String = "48680cbd326ad4245ee734b05722bab2"

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request()
            .url()
            .newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .build()

        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }

}