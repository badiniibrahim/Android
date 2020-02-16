package com.badini.movie.data.network

import com.badini.movie.data.model.api.ApiError
import org.json.JSONObject
import retrofit2.Response
import java.lang.StringBuilder

object ErrorUtils {

    fun parseError(response: Response<*>): ApiError {
        var success: Boolean = false
        val messages =  StringBuilder()

        try {
            val errorBody = response.errorBody()?.string()
            messages.append(errorBody)
        } catch (e: Exception) {
            e.printStackTrace()
            success = false
            messages.append("Unable to parse error")
        }
        return ApiError(messages)
    }

}