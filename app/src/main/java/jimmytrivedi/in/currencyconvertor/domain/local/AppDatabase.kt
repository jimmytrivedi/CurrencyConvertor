package jimmytrivedi.`in`.currencyconvertor.domain.local

import androidx.room.Database
import androidx.room.RoomDatabase
import jimmytrivedi.`in`.currencyconvertor.domain.local.data.ConversionHistoryEntity

@Database(
    entities = [
        ConversionHistoryEntity::class,
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getConversionHistoryDao(): IConversionHistoryDao
}
