package jimmytrivedi.`in`.currencyconvertor.app.history

import android.content.Context
import jimmytrivedi.`in`.currencyconvertor.R
import jimmytrivedi.`in`.currencyconvertor.app.base.BaseViewHolder
import jimmytrivedi.`in`.currencyconvertor.databinding.IncludeConversionHistoryLayoutBinding
import jimmytrivedi.`in`.currencyconvertor.domain.local.data.ConversionHistoryEntity
import jimmytrivedi.`in`.currencyconvertor.global.utility.AppUtils
import jimmytrivedi.`in`.currencyconvertor.global.utility.LogUtils

class ConversionHistoryViewHolder(
    private val context: Context,
    private val binding: IncludeConversionHistoryLayoutBinding,
) : BaseViewHolder(binding.root) {

    fun bind(entity: ConversionHistoryEntity) {
        binding.tvBaseCurrency.text = context.getString(R.string.base_currency_with_colon, entity.baseCurrency)
        binding.tvTargetCurrency.text = context.getString(R.string.target_currency_with_colon, entity.targetCurrency)
        binding.tvAmount.text = context.getString(R.string.amount_with_colon, entity.amount)
        binding.tvConvertedAmount.text = context.getString(R.string.converted_amount_with_colon, entity.result)
        LogUtils.d("Hello: t" + entity.timestamp)
        binding.tvTimestamp.text = context.getString(R.string.timestamp_with_colon, AppUtils.convertTimestampToDate(entity.timestamp))
    }
}

