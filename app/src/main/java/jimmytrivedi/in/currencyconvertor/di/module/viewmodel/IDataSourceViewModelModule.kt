package jimmytrivedi.`in`.currencyconvertor.di.module.viewmodel

import jimmytrivedi.`in`.currencyconvertor.di.qualifier.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import jimmytrivedi.`in`.currencyconvertor.di.qualifier.LocalDataSource
import jimmytrivedi.`in`.currencyconvertor.domain.local.ExchangeRateLocalDataSourceImpl
import jimmytrivedi.`in`.currencyconvertor.domain.local.IExchangeRateLocalDataSource
import jimmytrivedi.`in`.currencyconvertor.domain.remote.domain.exchangerate.ExchangeRateRemoteDataSourceImpl
import jimmytrivedi.`in`.currencyconvertor.domain.remote.domain.exchangerate.IExchangeRateRemoteDataSource


/**
 *  This class is used to provides all the bind-able Data sources (Remote & Local) - ViewModelComponent
 */
@InstallIn(ViewModelComponent::class)
@Module
interface IDataSourceViewModelModule {

    /**
     * This Remote data source will be for entire exchange rate module
     * We've specifically annotated with @RemoteDataSource because UserDataSource obj can be provided with @LocalDataSource as well
     */
    @Binds
    @RemoteDataSource
    fun bindExchangeRateRemoteDataSource(exchangeRateRemoteDataSourceImpl: ExchangeRateRemoteDataSourceImpl): IExchangeRateRemoteDataSource

    /**
     * This Local data source will be for entire exchange rate module
     * We've specifically annotated with @RemoteDataSource because UserDataSource obj can be provided with @LocalDataSource as well
     */
    @Binds
    @LocalDataSource
    fun bindExchangeRateLocalDataSource(exchangeRateLocalDataSourceImpl: ExchangeRateLocalDataSourceImpl): IExchangeRateLocalDataSource
}