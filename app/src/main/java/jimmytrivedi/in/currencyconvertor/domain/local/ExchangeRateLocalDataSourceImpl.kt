package jimmytrivedi.`in`.currencyconvertor.domain.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import jimmytrivedi.`in`.currencyconvertor.domain.local.data.ConversionHistoryEntity
import jimmytrivedi.`in`.currencyconvertor.global.sharedpreference.IPreferencesHelper
import jimmytrivedi.`in`.currencyconvertor.global.utility.AppConstant
import jimmytrivedi.`in`.currencyconvertor.domain.remote.networking.base.BaseDataSource
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
    private val historyDao: IConversionHistoryDao,
    private val preferencesHelper: IPreferencesHelper
) : BaseDataSource(context), IExchangeRateLocalDataSource {

    /** ------------------------------------ SHARED PREFERENCE ------------------------------------ **/
    override fun saveUserBaseCurrency(currency: Int) {
        preferencesHelper.saveInt(AppConstant.PreferenceKey.BASE_CURRENCY, currency)
    }

    override fun clearUserBaseCurrency(key: String) {
        preferencesHelper.clearValue(key)
    }

    override fun getUserBaseCurrency(): Flow<Int> = flow {
        emit(preferencesHelper.getInt(AppConstant.PreferenceKey.BASE_CURRENCY))
    }


    /** ------------------------------------ ROOM DB ------------------------------------ **/
    override fun insertConversionHistory(entity: ConversionHistoryEntity): Flow<Resource<Long?>> = getDBResult {
        historyDao.insertConversionHistory(entity)
    }

    override fun getAllConversionHistory(): Flow<Resource<List<ConversionHistoryEntity>?>> = getDBResult {
        historyDao.getAllConversionHistory()
    }
}