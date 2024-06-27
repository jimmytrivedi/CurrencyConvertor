package jimmytrivedi.`in`.currencycalculator.networking.domain.base

import android.content.Context
import android.util.Log
import jimmytrivedi.`in`.currencycalculator.R
import jimmytrivedi.`in`.currencycalculator.networking.global.NetworkConstant
import jimmytrivedi.`in`.currencycalculator.networking.global.NetworkUtils
import jimmytrivedi.`in`.currencycalculator.networking.global.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * This class is for entire project for all the modules
 */
abstract class BaseDataSource(private val context: Context) {

    /**
     * This function will make it separate success and failure cases as well as handle to exceptions
     */
    protected fun <T> getResult(requestTag: String, call: suspend () -> Response<T>): Flow<Resource<T>> = flow {
        var errorBody: String? = null
        var response: Response<T>? = null

        try {
            response = call()
            getResponseType(requestTag, response)
            val responseBody: T? = response.body()

            try {
                if (!response.isSuccessful || responseBody == null) {
                    errorBody = response.errorBody()?.string()
                }
            } catch (e: Exception) {
                Log.d("BaseDataSource", "traceException: " + e.printStackTrace() + "\n" + e.cause)
            }


            if (response.isSuccessful) {
                if (responseBody != null) {
                    emit(Resource.success(responseBody, response.message(), response.code(), false))
                } else {
                    emit(error(null, NetworkUtils.getErrorMessage(context, errorBody), response.code()))
                }
            } else {
                if (!NetworkUtils.isNetworkAvailable(context)) {
                    emit(error(message = context.getString(R.string.could_not_connect_to_network), code = NetworkConstant.NetworkValues.INTERNET_ERROR_CODE))
                } else {
                    emit(error(responseBody, NetworkUtils.getErrorMessage(context, errorBody), response.code()))
                }
            }
        } catch (e: Exception) {
            Log.d( "BaseDataSource", "exception: " + e.printStackTrace() + "\n" + e.cause)
            if (!NetworkUtils.isNetworkAvailable(context) || e is SocketTimeoutException || e is UnknownHostException) {
                emit(error(message = context.getString(R.string.could_not_connect_to_network), code = NetworkConstant.NetworkValues.INTERNET_ERROR_CODE))
            } else {
                emit(error(message = context.getString(R.string.there_seems_to_be_a_technical_issue), code = NetworkConstant.NetworkValues.DEFAULT_ERROR_CODE))
            }
        }
    }

    private fun <T> error(body: T? = null, message: String, code: Int): Resource<T> {
        return Resource.error(body, message, code, false)
    }

    private fun <T> getResponseType(requestTag: String, response: Response<T>?) {
        response?.let { mResponse->
            if (mResponse.raw().networkResponse != null) {
                Log.d( "BaseDataSource",  "ResponseType: NetworkResponse: $requestTag")
            }

            if (mResponse.raw().cacheResponse != null) {
                Log.d( "BaseDataSource","ResponseType: CacheResponse: $requestTag")
            }

            if (mResponse.raw().cacheResponse != null && mResponse.raw().networkResponse == null) {
                Log.d( "BaseDataSource","ResponseType: Concrete CacheResponse: $requestTag")
            }
        }

    }

    /**
     * This function will make it separate success and failure cases as well as handle to exceptions
     */
    protected fun <T> getDBResult(call: suspend () -> T): Flow<Resource<T>> = flow {
        try {
            Log.d( "BaseDataSource","getDBResult: first line")
            val response = call()
            Log.d( "BaseDataSource","getDBResult: $response")
            if (response != null) {
                emit(Resource.success(response, "Success", 200, false))
            } else {
                emit(error(message = context.getString(R.string.there_seems_to_be_a_technical_issue), code = NetworkConstant.NetworkValues.DEFAULT_ERROR_CODE))
            }
        } catch (e: Exception) {
            Log.d( "BaseDataSource", "getDBResult: " + e.printStackTrace() + "\n" + e.cause)
            emit(error(message = context.getString(R.string.there_seems_to_be_a_technical_issue), code = NetworkConstant.NetworkValues.DEFAULT_ERROR_CODE))
        }
    }
}