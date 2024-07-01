package jimmytrivedi.`in`.currencyconvertor.global.sharedpreference

interface IPreferencesHelper {
    fun saveString(key: String, value: String)
    fun getString(key: String): String
    fun saveBoolean(key: String, value: Boolean)
    fun getBoolean(key: String): Boolean
    fun saveInt(key: String, value: Int)
    fun getInt(key: String): Int
    fun clearValue(key: String)
}