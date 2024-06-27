package jimmytrivedi.`in`.currencycalculator.main

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jimmytrivedi.`in`.currencycalculator.base.BaseViewModel
import jimmytrivedi.`in`.currencycalculator.networking.data.exchangerate.ExchangeRateResponse
import jimmytrivedi.`in`.currencycalculator.networking.domain.exchangerate.IExchangeRateRepository
import jimmytrivedi.`in`.currencycalculator.networking.global.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyActivityViewModel @Inject constructor(private val repository: IExchangeRateRepository): BaseViewModel() {

    private val _exchangeRateData = MutableSharedFlow <Resource<ExchangeRateResponse>>()
    val exchangeRateData = _exchangeRateData.asSharedFlow()

    fun fetchExchangeRateData() {
        viewModelScope.launch {
            _exchangeRateData.emit(Resource.loading(true))
            repository.getExchangeRateData().collect {
                _exchangeRateData.emit(it)
            }
        }
    }

}