package jimmytrivedi.`in`.currencyconvertor.main

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jimmytrivedi.`in`.currencyconvertor.base.BaseViewModel
import jimmytrivedi.`in`.currencyconvertor.domain.remote.data.exchangerate.ExchangeRate
import jimmytrivedi.`in`.currencyconvertor.domain.remote.data.exchangerate.ExchangeRateResponse
import jimmytrivedi.`in`.currencyconvertor.domain.remote.domain.exchangerate.IExchangeRateRepository
import jimmytrivedi.`in`.currencyconvertor.domain.remote.global.Resource
import jimmytrivedi.`in`.currencyconvertor.global.utility.AppConstant
import jimmytrivedi.`in`.currencyconvertor.global.utility.LogUtils
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyActivityViewModel @Inject constructor(private val repository: IExchangeRateRepository): BaseViewModel() {

    private val _loadingStatus = MutableSharedFlow <Resource<ExchangeRateResponse>>()
    val loadingStatus = _loadingStatus.asSharedFlow()

    private val _exchangeRateData = MutableSharedFlow <Resource<ExchangeRateResponse>>()
    val exchangeRateData = _exchangeRateData.asSharedFlow()

    private val _baseCurrencyData = MutableSharedFlow <Int>()
    val baseCurrencyData = _baseCurrencyData.asSharedFlow()

    fun getExchangeRateData(exchangeRate: ExchangeRate) {
        viewModelScope.launch {
            _loadingStatus.emit(Resource.loading(true))
            repository.getExchangeRateData(exchangeRate).collect {
                _loadingStatus.emit(Resource.loading(false))
                _exchangeRateData.emit(it)
            }
        }
    }

    fun setBaseCurrencyData(baseCurrency: Int) {
        viewModelScope.launch {
            repository.saveUserBaseCurrency(baseCurrency)
        }
    }

    fun getBaseCurrencyData() {
        viewModelScope.launch {
            repository.getUserBaseCurrency().collect {
                _baseCurrencyData.emit(it)
            }
        }
    }

    fun clearBaseCurrencyData(key: String) {
        viewModelScope.launch {
            repository.clearUserBaseCurrency(key)
        }
    }

}