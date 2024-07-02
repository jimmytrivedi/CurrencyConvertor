package jimmytrivedi.`in`.currencyconvertor.global.utility

import android.text.Editable
import org.json.JSONArray
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.regex.Pattern

object AppUtils {

    fun checkValidation(text: String) : Boolean{
        val nPattern = "[1-9][0-9]*"
        val p = Pattern.compile(nPattern)
        val m = p.matcher(text)
        return m.matches()
    }

    fun checkValidation(view: Editable?) : Boolean{
        if (view.isNullOrEmpty()) {
            return false
        }
        return true
    }

    fun isNullOrEmptyList(list: List<Any>?): Boolean {
        if (list == null) {
            return true
        }

        list.let {
            if (it.isEmpty()) {
                return true
            }
        }
        return false
    }

    fun isNullOrEmptyList(arrayList: ArrayList<Any>?): Boolean {
        if (arrayList == null) {
            return true
        }

        arrayList.let {
            if (it.isEmpty()) {
                return true
            }
        }
        return false
    }

    fun isNullOrEmptyList(jsonArray: JSONArray?): Boolean {
        if (jsonArray == null) {
            return true
        }

        jsonArray.let {
            if (it.length() == 0) {
                return true
            }
        }
        return false
    }

    fun convertTimestampToDate(timestamp: Long): String {
        // Convert timestamp to Date object
        val date = Date(timestamp)

        // Define the desired format
        val sdf = SimpleDateFormat("dd-MMM-yyyy hh:mm a", Locale.getDefault())

        // Format the date object to a string
        return sdf.format(date)
    }
}