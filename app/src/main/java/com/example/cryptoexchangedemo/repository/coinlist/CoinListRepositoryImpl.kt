package com.example.cryptoexchangedemo.repository.coinlist

import com.example.cryptoexchangedemo.domain.models.CoinModel
import com.example.cryptoexchangedemo.network.CEDService
import com.example.cryptoexchangedemo.network.mapper.CoinListResponseMapper
import javax.inject.Inject

/**
 * Created by Batuhan Duvarci on 27.11.2021.
 */
class CoinListRepositoryImpl @Inject constructor(private val cedService: CEDService, private val mapper: CoinListResponseMapper): CoinListRepository {

    override suspend fun getCoinList(): List<CoinModel> {
        return mapper.fromEntityList(cedService.getCoinList().coinList)
    }
}