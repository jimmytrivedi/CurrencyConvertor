package jimmytrivedi.`in`.currencycalculator.di.module.viewmodel

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import jimmytrivedi.`in`.currencycalculator.networking.domain.exchangerate.ExchangeRateRepositoryImpl
import jimmytrivedi.`in`.currencycalculator.networking.domain.exchangerate.IExchangeRateRepository

/**
 *  This class is used to provides all the repositories view model level - ViewModelComponent
 */
@InstallIn(ViewModelComponent::class)
@Module
interface IRepositoryViewModelModule {

    /**
     * This Notification repository will be for entire Exchange rate
     */
    @Binds
    fun bindExchangeRateRepositoryImpl(exchangeRateRepositoryImpl: ExchangeRateRepositoryImpl): IExchangeRateRepository

}
