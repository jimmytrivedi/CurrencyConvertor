package jimmytrivedi.`in`.currencyconvertor.networking.global

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_ETHERNET
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import jimmytrivedi.`in`.currencyconvertor.R
import jimmytrivedi.`in`.currencyconvertor.global.LogUtils
import org.json.JSONException
import org.json.JSONObject

object NetworkUtils {
    fun getErrorMessage(context: Context, errorBody: String?): String {
        try {
            if (errorBody?.isNotEmpty() == true) {
                val errorJson = JSONObject(errorBody)
                val errorMessage = errorJson.optString("message")
                if (errorMessage.isNotBlank()) {
                    return errorMessage
                }
            }
        } catch (e: JSONException) {
            LogUtils.d("NetworkUtils getErrorMessage: " + e.printStackTrace() + "\n" + e.cause)
        }

        return context.getString(R.string.there_seems_to_be_a_technical_issue)
    }

    fun isNetworkAvailable(context: Context): Boolean {
        try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetwork ?: return false
            val capabilities = cm.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } catch (e: Exception) {
            return false
        }
    }
}