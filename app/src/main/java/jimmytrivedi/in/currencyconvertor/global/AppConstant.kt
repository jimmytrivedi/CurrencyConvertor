package jimmytrivedi.`in`.currencyconvertor.global

sealed class AppConstant {

    class Default : AppConstant() {
        companion object {
            const val BUILD_TYPE_DEBUG: String = "debug"
        }
    }
}