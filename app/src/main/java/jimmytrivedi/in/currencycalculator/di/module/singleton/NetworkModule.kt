package jimmytrivedi.`in`.currencycalculator.di.module.singleton

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jimmytrivedi.`in`.currencycalculator.BuildConfig
import jimmytrivedi.`in`.currencycalculator.networking.global.NetworkConstant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * This class is used to provide objects related to Network layer only
 */
@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(NetworkConstant.Default.BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()

        okHttpClientBuilder.readTimeout(NetworkConstant.NetworkValues.READ_WRITE_TIMEOUT, TimeUnit.SECONDS)
        okHttpClientBuilder.connectTimeout(NetworkConstant.NetworkValues.READ_WRITE_TIMEOUT, TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(NetworkConstant.NetworkValues.READ_WRITE_TIMEOUT, TimeUnit.SECONDS)

        val logger =
            HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) {
                    level = HttpLoggingInterceptor.Level.BODY
                } else {
                    level = HttpLoggingInterceptor.Level.NONE
                }
            }

        okHttpClientBuilder.addInterceptor(logger)
        return okHttpClientBuilder.build()
    }
}