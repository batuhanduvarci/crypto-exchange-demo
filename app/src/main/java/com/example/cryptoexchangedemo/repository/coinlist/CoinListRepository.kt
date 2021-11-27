package com.example.cryptoexchangedemo.repository.coinlist

import com.example.cryptoexchangedemo.domain.models.CoinModel

/**
 * Created by Batuhan Duvarci on 27.11.2021.
 */
interface CoinListRepository {

    suspend fun getCoinList(): List<CoinModel>
}