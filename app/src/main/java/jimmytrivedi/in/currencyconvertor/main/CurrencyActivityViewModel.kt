package jimmytrivedi.`in`.currencyconvertor.main

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jimmytrivedi.`in`.currencyconvertor.base.BaseViewModel
import jimmytrivedi.`in`.currencyconvertor.networking.data.exchangerate.ExchangeRate
import jimmytrivedi.`in`.currencyconvertor.networking.data.exchangerate.ExchangeRateResponse
import jimmytrivedi.`in`.currencyconvertor.networking.domain.exchangerate.IExchangeRateRepository
import jimmytrivedi.`in`.currencyconvertor.networking.global.Resource
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

    fun fetchExchangeRateData(exchangeRate: ExchangeRate) {
        viewModelScope.launch {
            _loadingStatus.emit(Resource.loading(true))
            repository.getExchangeRateData(exchangeRate).collect {
                _loadingStatus.emit(Resource.loading(false))
                _exchangeRateData.emit(it)
            }
        }
    }

}