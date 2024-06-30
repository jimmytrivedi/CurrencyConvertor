package jimmytrivedi.`in`.currencyconvertor.networking.global

import jimmytrivedi.`in`.currencyconvertor.BuildConfig

sealed class NetworkConstant {

    class Default : NetworkConstant() {
        companion object {
            const val BASE_URL = "https://api.freecurrencyapi.com/v1/"
            const val EXCHANGE_RATE_API_KEY = BuildConfig.EXCHANGE_RATE_API_KEY
        }
    }

    class APIEndPoint : NetworkConstant() {
        companion object {
            const val LATEST_DATA = "latest"
            const val API_KEY = "apikey"
            const val BASE_CURRENCY = "base_currency"
            const val TARGET_CURRENCY = "currencies"
        }
    }

    class NetworkValues : NetworkConstant() {
        companion object {
            const val DEFAULT_ERROR_CODE = -2
            const val INTERNET_ERROR_CODE = -1
            const val READ_WRITE_TIMEOUT: Long = 30
        }
    }

    class APIParsingTag : NetworkConstant() {
        companion object {
            const val EXCHANGE_RATE = "exchange_rate"
        }
    }
}