package com.example.cryptoexchangedemo.di

import com.example.cryptoexchangedemo.constants.ApplicationConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Batuhan Duvarci on 27.11.2021.
 */

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    fun baseUrlProvider() = ApplicationConstants.BASE_URL

    @Provides
    @Singleton
    fun clientProvider(): OkHttpClient {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder().addInterceptor(logger).build()
    }

    @Provides
    @Singleton
    fun retrofitProvider(okHttpClient: OkHttpClient, baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
}