package jimmytrivedi.`in`.currencyconvertor.domain.remote.networking.exchangerate

import jimmytrivedi.`in`.currencyconvertor.domain.remote.data.exchangerate.ExchangeRate
import jimmytrivedi.`in`.currencyconvertor.domain.remote.data.exchangerate.ExchangeRateResponse
import jimmytrivedi.`in`.currencyconvertor.domain.remote.global.Resource
import kotlinx.coroutines.flow.Flow

interface IExchangeRateRemoteDataSource {
    fun getExchageRateResponse(exchangeRate: ExchangeRate): Flow<Resource<ExchangeRateResponse>>
}