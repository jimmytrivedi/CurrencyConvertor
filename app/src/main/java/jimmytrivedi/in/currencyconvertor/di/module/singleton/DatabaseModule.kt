package jimmytrivedi.`in`.currencyconvertor.di.module.singleton

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jimmytrivedi.`in`.currencyconvertor.domain.local.AppDatabase
import jimmytrivedi.`in`.currencyconvertor.domain.local.IConversionHistoryDao
import jimmytrivedi.`in`.currencyconvertor.global.utility.AppConstant
import javax.inject.Singleton

/**
 *  This class is used to provides all DAO's
 */
@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext applicationContext: Context) : AppDatabase {
        val dbName = AppConstant.Default.DATABASE_NAME
        return Room.databaseBuilder(applicationContext, AppDatabase::class.java, dbName)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideConversionHistoryDao(database: AppDatabase): IConversionHistoryDao {
        return database.getConversionHistoryDao()
    }
}
