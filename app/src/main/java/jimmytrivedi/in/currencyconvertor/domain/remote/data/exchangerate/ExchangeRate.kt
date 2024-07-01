package jimmytrivedi.`in`.currencyconvertor.domain.remote.data.exchangerate

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExchangeRate(
    var totalAmount: String? = null,
    var baseCurrency: String? = null,
    var targetCurrency: String? = null,
) : Parcelable