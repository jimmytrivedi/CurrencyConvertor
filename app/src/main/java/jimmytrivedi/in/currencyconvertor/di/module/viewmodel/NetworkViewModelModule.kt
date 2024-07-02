package jimmytrivedi.`in`.currencyconvertor.di.module.viewmodel

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import jimmytrivedi.`in`.currencyconvertor.domain.remote.networking.exchangerate.IExchangeRateAPIService
import retrofit2.Retrofit

/**
 * This class is used to provide objects related to Network layer only - ViewModelComponent
 */
@InstallIn(ViewModelComponent::class)
@Module
object NetworkViewModelModule {

    /**
     * This service will be for entire user module
     */
    @Provides
    fun provideExchangeRateRetrofitService(retrofit: Retrofit): IExchangeRateAPIService = retrofit.create(
        IExchangeRateAPIService::class.java)

}
