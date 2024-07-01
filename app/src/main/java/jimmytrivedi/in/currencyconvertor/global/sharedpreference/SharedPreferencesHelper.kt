package jimmytrivedi.`in`.currencyconvertor.global.sharedpreference

import android.content.Context
import android.content.SharedPreferences
import jimmytrivedi.`in`.currencyconvertor.global.utility.AppConstant

class SharedPreferencesHelper(context: Context) : IPreferencesHelper {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(AppConstant.Default.PREFERENCE_NAME, Context.MODE_PRIVATE)

    override fun saveString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun getString(key: String): String {
        return sharedPreferences.getString(key, null) ?: ""
    }

    override fun saveBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    override fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    override fun saveInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    override fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, -1)
    }

    override fun clearValue(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }
}

