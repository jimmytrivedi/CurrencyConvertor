package jimmytrivedi.`in`.currencyconvertor.domain.remote.domain.exchangerate

import jimmytrivedi.`in`.currencyconvertor.domain.remote.data.exchangerate.ExchangeRateResponse
import jimmytrivedi.`in`.currencyconvertor.domain.remote.global.NetworkConstant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Tag

interface IExchangeRateAPIService {
    @GET(NetworkConstant.APIEndPoint.LATEST_DATA)
    suspend fun getExchangeRateData(
        @Tag requestTag: String,
        @Query(NetworkConstant.APIEndPoint.API_KEY) value: String = NetworkConstant.Default.EXCHANGE_RATE_API_KEY,
        @Query(NetworkConstant.APIEndPoint.BASE_CURRENCY) baseCurrency: String?,
        @Query(NetworkConstant.APIEndPoint.TARGET_CURRENCY) targetCurrency: String?,
    ): Response<ExchangeRateResponse>
}