package jimmytrivedi.`in`.currencyconvertor.domain.remote.networking.exchangerate

import jimmytrivedi.`in`.currencyconvertor.domain.local.data.ConversionHistoryEntity
import jimmytrivedi.`in`.currencyconvertor.domain.remote.data.exchangerate.ExchangeRate
import jimmytrivedi.`in`.currencyconvertor.domain.remote.data.exchangerate.ExchangeRateResponse
import jimmytrivedi.`in`.currencyconvertor.domain.remote.global.Resource
import kotlinx.coroutines.flow.Flow

interface IExchangeRateRepository {
    fun getExchangeRateData(exchangeRate: ExchangeRate): Flow<Resource<ExchangeRateResponse>>

    fun saveUserBaseCurrency(currency: Int)

    fun clearUserBaseCurrency(key: String)

    fun getUserBaseCurrency(): Flow<Int>

    fun insertConversionHistory(entity: ConversionHistoryEntity): Flow<Resource<Long?>>

    fun getAllConversionHistory():  Flow<Resource<List<ConversionHistoryEntity>?>>
}