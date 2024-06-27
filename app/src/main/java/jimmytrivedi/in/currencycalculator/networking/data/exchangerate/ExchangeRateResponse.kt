package jimmytrivedi.`in`.currencycalculator.networking.data.exchangerate

data class ExchangeRateResponse(
    var base: String?,
    var disclaimer: String?,
    var license: String?,
    var rates: Rates?,
    var timestamp: Int?
)