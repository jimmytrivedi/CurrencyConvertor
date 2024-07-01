package jimmytrivedi.`in`.currencyconvertor.di.module.singleton

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jimmytrivedi.`in`.currencyconvertor.global.sharedpreference.IPreferencesHelper
import jimmytrivedi.`in`.currencyconvertor.global.sharedpreference.SharedPreferencesHelper
import javax.inject.Singleton

/**
 * This class is used to provide objects related to App layer only
 */
@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferencesHelper(@ApplicationContext context: Context): IPreferencesHelper {
        return SharedPreferencesHelper(context)
    }
}