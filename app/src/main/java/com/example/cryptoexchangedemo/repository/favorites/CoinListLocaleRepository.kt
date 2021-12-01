package com.example.cryptoexchangedemo.repository.favorites

import com.example.cryptoexchangedemo.domain.models.CoinEntityModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by Batuhan Duvarci on 30.11.2021.
 */
interface CoinListLocaleRepository {

    suspend fun getCoinList(): Flow<List<CoinEntityModel>>

    suspend fun insertCoinId(coinId: String)

    suspend fun removeCoinId(coin: CoinEntityModel)
}