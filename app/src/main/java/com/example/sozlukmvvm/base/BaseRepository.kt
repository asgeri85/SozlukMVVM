package com.example.sozlukmvvm.base

import com.example.sozlukmvvm.model.ErrorResponseModel
import com.example.sozlukmvvm.utils.NetworkResult
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.lang.Exception

abstract class BaseRepository {

    suspend fun <T> safeApiRequest(apiRequest: suspend () -> T): NetworkResult<T> {
        return withContext(Dispatchers.IO) {
            try {
                NetworkResult.Success(apiRequest.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        NetworkResult.Error(false, errorParse(throwable.response()?.errorBody()?.string()))
                    }
                    else->NetworkResult.Error(true,throwable.localizedMessage)
                }
            }
        }
    }
}

private fun errorParse(error: String?): String {
    error.let {
        return try {
            val errorReponse = Gson().fromJson(error, ErrorResponseModel::class.java)
            val errorMessage = errorReponse.message
            errorMessage ?: "Bilinmeyen hata oluştu"
        } catch (e: Exception) {
            "Bilinmeyen hata oluştu"
        }
    }

    return "Bilinmeyen hata oluştu"

}