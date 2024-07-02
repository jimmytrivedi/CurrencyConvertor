package jimmytrivedi.`in`.currencyconvertor.domain.remote.networking.exchangerate

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import jimmytrivedi.`in`.currencyconvertor.domain.remote.data.exchangerate.ExchangeRate
import jimmytrivedi.`in`.currencyconvertor.domain.remote.data.exchangerate.ExchangeRateResponse
import jimmytrivedi.`in`.currencyconvertor.domain.remote.networking.base.BaseDataSource
import jimmytrivedi.`in`.currencyconvertor.domain.remote.global.NetworkConstant
import jimmytrivedi.`in`.currencyconvertor.domain.remote.global.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * This class is specific for entire Exchange module only
 */
class ExchangeRateRemoteDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val apiService: IExchangeRateAPIService,
) : BaseDataSource(context), IExchangeRateRemoteDataSource {

    override fun getExchageRateResponse(exchangeRate: ExchangeRate): Flow<Resource<ExchangeRateResponse>> = getResult(requestTag = NetworkConstant.APIParsingTag.EXCHANGE_RATE) {
        apiService.getExchangeRateData(
            requestTag = NetworkConstant.APIParsingTag.EXCHANGE_RATE,
            baseCurrency = exchangeRate.baseCurrency,
            targetCurrency = exchangeRate.targetCurrency
        )
    }
}