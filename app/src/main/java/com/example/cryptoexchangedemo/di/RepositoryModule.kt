package com.example.cryptoexchangedemo.di

import com.example.cryptoexchangedemo.network.CEDService
import com.example.cryptoexchangedemo.network.mapper.CoinDetailResponseMapper
import com.example.cryptoexchangedemo.network.mapper.CoinListResponseMapper
import com.example.cryptoexchangedemo.repository.coinlist.CoinListRemoteRepository
import com.example.cryptoexchangedemo.repository.coinlist.CoinListRemoteRepositoryImpl
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
    fun coinRepositoryProvider(
        cedService: CEDService,
        coinListResponseMapper: CoinListResponseMapper,
        coinDetailResponseMapper: CoinDetailResponseMapper
    ): CoinListRemoteRepository {
        return CoinListRemoteRepositoryImpl(
            cedService,
            coinListResponseMapper,
            coinDetailResponseMapper
        )
    }
}