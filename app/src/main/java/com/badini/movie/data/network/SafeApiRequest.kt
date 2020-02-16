package com.badini.movie.data.network

import com.badini.movie.data.model.api.ApiError
import com.badini.movie.utils.ApiException
import retrofit2.Response
import java.lang.StringBuilder

abstract class SafeApiRequest {

    suspend fun<T :  Any> apiRequest(call : suspend () -> Response<T>) : Response<T> {
         val response = call.invoke()

        if (response.isSuccessful){
            return response
        }else {
            //TODO : Revoir la gestion des erreurs
            val apiError: ApiError = ErrorUtils.parseError(response);
            val message = StringBuilder()
            message.append(apiError.errors)
            throw ApiException(message.toString())
        }
    }
}