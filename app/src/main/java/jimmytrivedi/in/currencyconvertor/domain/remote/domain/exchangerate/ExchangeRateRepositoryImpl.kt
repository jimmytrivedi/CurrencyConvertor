package jimmytrivedi.`in`.currencyconvertor.domain.remote.domain.exchangerate

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import jimmytrivedi.`in`.currencyconvertor.di.qualifier.LocalDataSource
import jimmytrivedi.`in`.currencyconvertor.di.qualifier.RemoteDataSource
import jimmytrivedi.`in`.currencyconvertor.domain.local.IExchangeRateLocalDataSource
import jimmytrivedi.`in`.currencyconvertor.global.sharedpreference.IPreferencesHelper
import jimmytrivedi.`in`.currencyconvertor.global.utility.LogUtils
import jimmytrivedi.`in`.currencyconvertor.domain.remote.data.exchangerate.Data
import jimmytrivedi.`in`.currencyconvertor.domain.remote.data.exchangerate.ExchangeRate
import jimmytrivedi.`in`.currencyconvertor.domain.remote.data.exchangerate.ExchangeRateResponse
import jimmytrivedi.`in`.currencyconvertor.domain.remote.domain.base.BaseRepository
import jimmytrivedi.`in`.currencyconvertor.domain.remote.global.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.reflect.Field
import javax.inject.Inject


/**
 * This class is specific for entire Exchange module only
 */
class ExchangeRateRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @RemoteDataSource private val remoteDataSource: IExchangeRateRemoteDataSource,
    @LocalDataSource private val localDataSource: IExchangeRateLocalDataSource
) : BaseRepository(context), IExchangeRateRepository {

    override fun getExchangeRateData(exchangeRate: ExchangeRate): Flow<Resource<ExchangeRateResponse>> = flow {
        remoteDataSource.getExchageRateResponse(exchangeRate).collect {
            if (it.status ==  Resource.Status.SUCCESS) {
                it.data?.let {
                    try {
                        val currencyValue = getExchangeRate(it.data, exchangeRate.targetCurrency)
                        if (currencyValue != null) {
                            val finalValue = (exchangeRate.totalAmount?.toDouble() ?: 0.0) *  currencyValue
                            // Format the value to two decimal places
                            val formattedValue = String.format("%.2f", finalValue)
                            it.data?.result = formattedValue
                        }
                    } catch (e: Exception) {
                        LogUtils.d("Exception: " + e.message)
                    }
                }
            }

            emit(it)
        }
    }

    override fun saveUserBaseCurrency(currency: Int) {
        localDataSource.saveUserBaseCurrency(currency)
    }

    override fun clearUserBaseCurrency(key: String) {
        localDataSource.clearUserBaseCurrency(key)
    }

    override fun getUserBaseCurrency(): Flow<Int> = flow {
        localDataSource.getUserBaseCurrency().collect {
            emit(it)
        }
    }

    private fun getExchangeRate(data: Data?, currencyCode: String?): Double? {
        if (data == null || currencyCode == null) return null
        val field: Field? = try {
            Data::class.java.getDeclaredField(currencyCode)
        } catch (e: NoSuchFieldException) {
            null
        }

        field?.let {
            it.isAccessible = true
            return it.get(data) as? Double
        }

        return null
    }
}