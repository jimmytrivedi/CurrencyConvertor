package jimmytrivedi.`in`.currencyconvertor.global

import android.text.Editable
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
}