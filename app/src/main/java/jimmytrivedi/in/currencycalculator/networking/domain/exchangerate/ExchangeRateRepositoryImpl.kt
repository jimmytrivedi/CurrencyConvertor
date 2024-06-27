package jimmytrivedi.`in`.currencycalculator.networking.domain.exchangerate

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import jimmytrivedi.`in`.currencycalculator.di.qualifier.RemoteDataSource
import jimmytrivedi.`in`.currencycalculator.networking.data.exchangerate.ExchangeRateResponse
import jimmytrivedi.`in`.currencycalculator.networking.domain.base.BaseRepository
import jimmytrivedi.`in`.currencycalculator.networking.global.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * This class is specific for entire Exchange module only
 */
class ExchangeRateRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @RemoteDataSource private val remoteDataSource: IExchangeRateRemoteDataSource
) : BaseRepository(context), IExchangeRateRepository {

    override fun getExchangeRateData(): Flow<Resource<ExchangeRateResponse>> = flow {
        remoteDataSource.getExchageRateResponse().collect {
            emit(it)
        }
    }
}