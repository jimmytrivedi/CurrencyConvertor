package jimmytrivedi.`in`.currencycalculator.networking.global

sealed class NetworkConstant {

    class Default : NetworkConstant() {
        companion object {
            const val BASE_URL = "https://openexchangerates.org/api/"
            const val EXCHANGE_RATE_API_KEY = "" //todo make flavor setup and add both dev and prod key
        }
    }

    class APIEndPoint : NetworkConstant() {
        companion object {
            const val LATEST_JSON = "latest.json"
            const val CURRENCIES_JSON = "currencies.json"
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