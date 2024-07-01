package jimmytrivedi.`in`.currencyconvertor.global.utility

sealed class AppConstant {

    class Default : AppConstant() {
        companion object {
            const val BUILD_TYPE_DEBUG: String = "debug"
            const val PREFERENCE_NAME: String = "CurrencyPrefs"
        }
    }

    /**
     * For Shared Preference keys & values
     */
    class PreferenceKey : AppConstant() {
        companion object {
            const val BASE_CURRENCY = "base_currency"

        }
    }
}