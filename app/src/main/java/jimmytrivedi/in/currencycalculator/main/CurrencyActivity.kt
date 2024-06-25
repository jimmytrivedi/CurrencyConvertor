package jimmytrivedi.`in`.currencycalculator.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
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
        initViews()

    }

    override fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

            }
        }
    }

    override fun getBundleParameters(bundle: Bundle) {}

    private fun initViews() {
    }
}
