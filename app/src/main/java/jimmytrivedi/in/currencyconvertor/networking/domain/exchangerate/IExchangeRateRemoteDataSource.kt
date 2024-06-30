package jimmytrivedi.`in`.currencyconvertor.networking.domain.exchangerate

import jimmytrivedi.`in`.currencyconvertor.networking.data.exchangerate.ExchangeRate
import jimmytrivedi.`in`.currencyconvertor.networking.data.exchangerate.ExchangeRateResponse
import jimmytrivedi.`in`.currencyconvertor.networking.global.Resource
import kotlinx.coroutines.flow.Flow

interface IExchangeRateRemoteDataSource {
    fun getExchageRateResponse(exchangeRate: ExchangeRate): Flow<Resource<ExchangeRateResponse>>
}