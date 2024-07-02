package jimmytrivedi.`in`.currencyconvertor.domain.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import jimmytrivedi.`in`.currencyconvertor.domain.local.data.ConversionHistoryEntity

@Dao
interface IConversionHistoryDao {
    /**
     * This methods get all the notifications.
     */
    @Query("SELECT * FROM CONVERSION_HISTORY ORDER BY timestamp DESC")
    suspend fun getAllConversionHistory(): List<ConversionHistoryEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConversionHistory(history: ConversionHistoryEntity): Long?
}