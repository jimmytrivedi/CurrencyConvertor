package jimmytrivedi.`in`.currencycalculator.main

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jimmytrivedi.`in`.currencycalculator.base.BaseViewModel
import jimmytrivedi.`in`.currencycalculator.networking.data.exchangerate.ExchangeRate
import jimmytrivedi.`in`.currencycalculator.networking.data.exchangerate.ExchangeRateResponse
import jimmytrivedi.`in`.currencycalculator.networking.domain.exchangerate.IExchangeRateRepository
import jimmytrivedi.`in`.currencycalculator.networking.global.Resource
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