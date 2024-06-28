package jimmytrivedi.`in`.currencycalculator.networking.domain.exchangerate

import jimmytrivedi.`in`.currencycalculator.networking.data.exchangerate.ExchangeRateResponse
import jimmytrivedi.`in`.currencycalculator.networking.global.NetworkConstant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Tag

interface IExchangeRateAPIService {
    @GET(NetworkConstant.APIEndPoint.LATEST_JSON)
    suspend fun getExchangeRateData(
        @Tag requestTag: String,
        @Query(NetworkConstant.APIEndPoint.APP_ID) value: String = NetworkConstant.Default.EXCHANGE_RATE_API_KEY
    ): Response<ExchangeRateResponse>
}