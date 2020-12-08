package com.lucky.task.base

import com.ahmoneam.basecleanarchitecture.base.data.model.BaseResponse
import com.lucky.task.R
import com.lucky.task.utils.ConnectivityUtils
import com.lucky.task.utils.coroutines.ContextProviders
import com.lucky.task.utils.network.ApplicationException
import com.lucky.task.utils.network.ErrorType
import com.lucky.task.utils.network.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.withContext
import retrofit2.Response
import timber.log.Timber

open class BaseRepository(val contextProvider: ContextProviders, private val connectivityUtils: ConnectivityUtils) {

    private val gSon = Gson()
    private val noInternetError = Result.Error(
        ApplicationException(
            type = ErrorType.Network.NoInternetConnection,
            errorMessageRes = R.string.error_no_internet_connection
        )
    )

    val unexpectedError = Result.Error(ApplicationException(type = ErrorType.Network.Unexpected))
    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): Result<T> {
        return withContext(contextProvider.IO) {
            return@withContext try {
                // check internet connection
                if (connectivityUtils.isNetworkConnected.not()) return@withContext noInternetError

                // make api call
                val response = call()

                // check response and convert to result
                return@withContext handleResult(response)

            } catch (error: Throwable) {
                Timber.e(error)
                unexpectedError(error)
            }
        }
    }

    private fun unexpectedError(error: Throwable): Result.Error {
        return Result.Error(
            ApplicationException(
                throwable = error,
                type = ErrorType.Network.Unexpected
            )
        )
    }
    private fun <T : Any> handleResult(response: Response<T>): Result<T> {
        return when (response.code()) {
            in 1..399 -> Result.Success(response.body()!!)
            401 -> Result.Error(
                ApplicationException(
                    type = ErrorType.Network.Unauthorized,
                    errorMessage = getErrorMessage(response)
                )
            )
            404 -> Result.Error(
                ApplicationException(
                    type = ErrorType.Network.ResourceNotFound,
                    errorMessage = getErrorMessage(response)
                )
            )
            else -> Result.Error(
                ApplicationException(
                    type = ErrorType.Network.Unexpected,
                    errorMessage = getErrorMessage(response)
                )
            )
        }
    }
    private fun <T> getErrorMessage(response: Response<T>): String? {
        return gSon.fromJson<BaseResponse<*>>(
            response.errorBody()?.string(),
            object : TypeToken<BaseResponse<*>>() {}.type
        ).error?.message
    }
}