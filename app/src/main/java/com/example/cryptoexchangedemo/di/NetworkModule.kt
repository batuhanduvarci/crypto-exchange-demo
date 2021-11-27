package com.example.cryptoexchangedemo.di

import com.example.cryptoexchangedemo.network.CEDService
import com.example.cryptoexchangedemo.network.mapper.CoinListResponseMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Batuhan Duvarci on 27.11.2021.
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun serviceProvider(retrofit: Retrofit) : CEDService = retrofit.create(CEDService::class.java)

    @Singleton
    @Provides
    fun coinListResponseMapperProvider(): CoinListResponseMapper{
        return CoinListResponseMapper()
    }
}