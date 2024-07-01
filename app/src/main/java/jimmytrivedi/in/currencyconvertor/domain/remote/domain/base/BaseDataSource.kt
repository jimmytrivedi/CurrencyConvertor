package jimmytrivedi.`in`.currencyconvertor.domain.remote.domain.base

import android.content.Context
import jimmytrivedi.`in`.currencyconvertor.R
import jimmytrivedi.`in`.currencyconvertor.global.utility.LogUtils
import jimmytrivedi.`in`.currencyconvertor.domain.remote.global.NetworkConstant
import jimmytrivedi.`in`.currencyconvertor.domain.remote.global.NetworkUtils
import jimmytrivedi.`in`.currencyconvertor.domain.remote.global.Resource
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
                LogUtils.d("BaseDataSource traceException: " + e.printStackTrace() + "\n" + e.cause)
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
            LogUtils.d( "BaseDataSource exception: " + e.printStackTrace() + "\n" + e.cause)
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
                LogUtils.d( "BaseDataSource ResponseType: NetworkResponse: $requestTag")
            }

            if (mResponse.raw().cacheResponse != null) {
                LogUtils.d( "BaseDataSource esponseType: CacheResponse: $requestTag")
            }

            if (mResponse.raw().cacheResponse != null && mResponse.raw().networkResponse == null) {
                LogUtils.d( "BaseDataSource ResponseType: Concrete CacheResponse: $requestTag")
            }
        }

    }
}