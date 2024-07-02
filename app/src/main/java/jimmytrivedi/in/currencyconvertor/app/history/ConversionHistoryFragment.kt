package jimmytrivedi.`in`.currencyconvertor.app.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import jimmytrivedi.`in`.currencyconvertor.app.base.BaseFragment
import jimmytrivedi.`in`.currencyconvertor.app.main.CurrencyActivityViewModel
import jimmytrivedi.`in`.currencyconvertor.databinding.FragmentConversionHistoryBinding
import jimmytrivedi.`in`.currencyconvertor.domain.local.data.ConversionHistoryEntity
import jimmytrivedi.`in`.currencyconvertor.global.utility.LogUtils
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ConversionHistoryFragment : BaseFragment() {
    private lateinit var binding: FragmentConversionHistoryBinding
    private val viewModel: CurrencyActivityViewModel by activityViewModels()
    private var adapter: ConversionHistoryAdapter? = null

    companion object {
        fun newInstance(bundle: Bundle?): ConversionHistoryFragment {
            val fragment = ConversionHistoryFragment()
            bundle?.let {
                fragment.arguments = bundle
            }
            return fragment
        }
    }

    /** ----------------------------------- overridden functions ----------------------------------- **/
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        if(!::binding.isInitialized) {
            binding = FragmentConversionHistoryBinding.inflate(inflater, container, false)
        }
        return binding.root
    }

    override fun initObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.conversionHistoryData.collect {
                    it.data?.let {
                        if (it.isNotEmpty()) {
                            adapter?.updateAdapter(it)
                        }
                    }
                }
            }
        }
    }

    override fun getBundleParameters(bundle: Bundle) {}

    override fun init() {
        viewModel.getAllConversionHistory()
    }

    override fun initViews(view: View) {
        setAdapter()
    }

    private fun setAdapter() {
        if (adapter == null) {
            adapter = ConversionHistoryAdapter(arrayListOf())
        }

        binding.recyclerView.adapter = adapter

        /**
         * Please do not remove this line. In specific to Samsung devices, we were getting one crash for recyclerview
         * @link https://stackoverflow.com/questions/31759171/recyclerview-and-java-lang-indexoutofboundsexception-inconsistency-detected-in?answertab=scoredesc#tab-top
         */
        binding.recyclerView.itemAnimator = null
    }

}