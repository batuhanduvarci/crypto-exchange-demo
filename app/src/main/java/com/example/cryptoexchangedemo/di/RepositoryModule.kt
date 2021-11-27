package com.example.cryptoexchangedemo.di

import com.example.cryptoexchangedemo.network.CEDService
import com.example.cryptoexchangedemo.network.mapper.CoinListResponseMapper
import com.example.cryptoexchangedemo.repository.coinlist.CoinListRepository
import com.example.cryptoexchangedemo.repository.coinlist.CoinListRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Batuhan Duvarci on 27.11.2021.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun coinListRepositoryProvider(
        cedService: CEDService,
        coinListResponseMapper: CoinListResponseMapper
    ): CoinListRepository{
        return CoinListRepositoryImpl(
            cedService,
            coinListResponseMapper
        )
    }
}