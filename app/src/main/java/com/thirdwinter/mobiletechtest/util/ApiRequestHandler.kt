package com.thirdwinter.mobiletechtest.util

import android.util.Log
import com.google.android.gms.common.api.ApiException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class ApiRequestHandler {

    suspend fun <T : Any> requestHandler(call: suspend () -> Response<T>): T {

        try {

            val response = call.invoke()

            return response.body()!!

        } catch (e: Exception) {

            throw Exception("Unable to reach server")

        }
    }

}