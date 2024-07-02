package jimmytrivedi.`in`.currencyconvertor.app.history

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import jimmytrivedi.`in`.currencyconvertor.R
import jimmytrivedi.`in`.currencyconvertor.app.base.BaseAdapter
import jimmytrivedi.`in`.currencyconvertor.app.base.BaseViewHolder
import jimmytrivedi.`in`.currencyconvertor.databinding.IncludeConversionHistoryLayoutBinding
import jimmytrivedi.`in`.currencyconvertor.domain.local.data.ConversionHistoryEntity

private lateinit var binding: ViewBinding

/**
 * This is a Entire History page Adapter class. This will work below toolbar
 */
class ConversionHistoryAdapter(
    private val history: ArrayList<ConversionHistoryEntity>
) : BaseAdapter() {

    /**
     * Naming convention:
     * For all the ViewHolder, provide name as [CartXYZSectionViewHolder]
     * For all the layout, provide name as [include_xyz_section_layout]
     */
    override fun onCreateBaseViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        binding = setViewBinding(parent, R.layout.include_conversion_history_layout)
        return ConversionHistoryViewHolder(getContext(parent) , binding as IncludeConversionHistoryLayoutBinding)
    }

    override fun onBindBaseViewHolder(holder: BaseViewHolder, position: Int) {
        (holder as? ConversionHistoryViewHolder)?.bind(history[position])
    }

    /**
     * To return size of widget need to be displayed.
     */
    override fun getItemBaseCount(): Int {
        return history.size
    }

    override fun getViewBinding(): ViewBinding {
        return binding
    }

    fun updateAdapter(historyList: List<ConversionHistoryEntity>) {
        history.clear()
        history.addAll(historyList)
        notifyBaseDataSetChanged()
    }
}