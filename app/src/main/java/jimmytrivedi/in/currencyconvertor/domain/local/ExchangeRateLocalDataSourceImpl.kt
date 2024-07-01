package jimmytrivedi.`in`.currencyconvertor.domain.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import jimmytrivedi.`in`.currencyconvertor.global.sharedpreference.IPreferencesHelper
import jimmytrivedi.`in`.currencyconvertor.global.utility.AppConstant
import jimmytrivedi.`in`.currencyconvertor.domain.remote.data.exchangerate.ExchangeRate
import jimmytrivedi.`in`.currencyconvertor.domain.remote.data.exchangerate.ExchangeRateResponse
import jimmytrivedi.`in`.currencyconvertor.domain.remote.domain.base.BaseDataSource
import jimmytrivedi.`in`.currencyconvertor.domain.remote.global.NetworkConstant
import jimmytrivedi.`in`.currencyconvertor.domain.remote.global.Resource
import jimmytrivedi.`in`.currencyconvertor.global.utility.LogUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * This class is specific for entire Exchange module only
 */
class ExchangeRateLocalDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val preferencesHelper: IPreferencesHelper
) : BaseDataSource(context), IExchangeRateLocalDataSource {


    override fun saveUserBaseCurrency(currency: Int) {
        preferencesHelper.saveInt(AppConstant.PreferenceKey.BASE_CURRENCY, currency)
    }

    override fun clearUserBaseCurrency(key: String) {
        preferencesHelper.clearValue(key)
    }

    override fun getUserBaseCurrency(): Flow<Int> = flow {
        emit(preferencesHelper.getInt(AppConstant.PreferenceKey.BASE_CURRENCY))
    }
}