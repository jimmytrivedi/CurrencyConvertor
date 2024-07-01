package jimmytrivedi.`in`.currencyconvertor.domain.local

import kotlinx.coroutines.flow.Flow

interface IExchangeRateLocalDataSource {
    fun saveUserBaseCurrency(currency: Int)

    fun clearUserBaseCurrency(key: String)

    fun getUserBaseCurrency(): Flow<Int>
}