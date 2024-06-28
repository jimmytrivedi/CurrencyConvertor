package jimmytrivedi.`in`.currencycalculator.main

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import jimmytrivedi.`in`.currencycalculator.R
import jimmytrivedi.`in`.currencycalculator.base.BaseActivity
import jimmytrivedi.`in`.currencycalculator.databinding.ActivityCurrencyBinding
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CurrencyActivity : BaseActivity() {
    private lateinit var binding: ActivityCurrencyBinding
    private val viewModel: CurrencyActivityViewModel by viewModels()


    override fun init() {
        binding = ActivityCurrencyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.fetchExchangeRateData()
        initViews()
    }

    override fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loadingStatus.collect {
                    if (it.state) {
                        binding.progressBar.show()
                    } else {
                        binding.progressBar.hide()
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.exchangeRateData.collect {
                    showLog(it.status)
                }
            }
        }
    }

    override fun getBundleParameters(bundle: Bundle) {}

    private fun initViews() {
        setSpinner()
    }

    private fun setSpinner() {
        val currencies = resources.getStringArray(R.array.currency_array)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerFromCurrency.adapter = adapter
        binding.spinnerToCurrency.adapter = adapter

    }
}
