package jimmytrivedi.`in`.currencyconvertor.domain.local.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "CONVERSION_HISTORY")
data class ConversionHistoryEntity(
    @PrimaryKey
    val historyId: String = UUID.randomUUID().toString(),
    val baseCurrency: String,
    val targetCurrency: String,
    val amount: String,
    val result: String,
    val timestamp: Long = System.currentTimeMillis()
)
