package jimmytrivedi.`in`.currencyconvertor.domain.local

import jimmytrivedi.`in`.currencyconvertor.domain.local.data.ConversionHistoryEntity
import jimmytrivedi.`in`.currencyconvertor.domain.remote.global.Resource
import kotlinx.coroutines.flow.Flow

interface IExchangeRateLocalDataSource {
    fun saveUserBaseCurrency(currency: Int)

    fun clearUserBaseCurrency(key: String)

    fun getUserBaseCurrency(): Flow<Int>

    fun insertConversionHistory(entity: ConversionHistoryEntity): Flow<Resource<Long?>>

    fun getAllConversionHistory(): Flow<Resource<List<ConversionHistoryEntity>?>>
}