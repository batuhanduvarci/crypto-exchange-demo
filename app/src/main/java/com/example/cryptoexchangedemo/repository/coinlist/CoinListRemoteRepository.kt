package com.example.cryptoexchangedemo.repository.coinlist

import com.example.cryptoexchangedemo.domain.models.CoinDetailModel
import com.example.cryptoexchangedemo.domain.models.CoinModel

/**
 * Created by Batuhan Duvarci on 27.11.2021.
 */
interface CoinListRemoteRepository {

    suspend fun getCoinList(): List<CoinModel>

    suspend fun getCoin(coinId: String): CoinDetailModel?
}