package jimmytrivedi.`in`.currencycalculator.networking.domain.exchangerate

import jimmytrivedi.`in`.currencycalculator.networking.data.exchangerate.ExchangeRateResponse
import jimmytrivedi.`in`.currencycalculator.networking.global.Resource
import kotlinx.coroutines.flow.Flow

interface IExchangeRateRemoteDataSource {
    fun getExchageRateResponse(): Flow<Resource<ExchangeRateResponse>>
}