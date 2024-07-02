package jimmytrivedi.`in`.currencyconvertor.global.utility

sealed class AppConstant {

    class Default : AppConstant() {
        companion object {
            const val BUILD_TYPE_DEBUG: String = "debug"
            const val PREFERENCE_NAME: String = "CurrencyPrefs"
            const val DATABASE_NAME: String = "CurrencyDB"
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

    class Animation : AppConstant() {
        companion object {
            const val FADE_ANIMATION: Int = 0
            const val LEFT_TO_RIGHT: Int = 1
            const val RIGHT_TO_LEFT: Int = 2
            const val BOTTOM_TO_TOP: Int = 4
            const val NONE: Int = 5
            const val SLIDE_FORM_BOTTOM_TO_TOP: Int = 8
            const val SLIDE_FROM_TOP_TO_BOTTOM: Int = 9
            const val DEFAULT: Int = 10
            const val FLIP: Int = 11
            const val FADE_IN_FADE_OUT: Int = 12

        }
    }
}