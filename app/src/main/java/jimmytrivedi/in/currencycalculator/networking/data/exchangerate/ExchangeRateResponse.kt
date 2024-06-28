package jimmytrivedi.`in`.currencycalculator.networking.data.exchangerate

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExchangeRateResponse(
    val error: Boolean = false,
    val status: Int?,
    val message: String?,
    val description: String?,
    var base: String?,
    var disclaimer: String?,
    var license: String?,
    var rates: Rates?,
    var timestamp: Int?
) : Parcelable