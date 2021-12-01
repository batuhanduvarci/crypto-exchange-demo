package com.example.cryptoexchangedemo.repository.coinlist

import com.example.cryptoexchangedemo.domain.models.CoinDetailModel
import com.example.cryptoexchangedemo.domain.models.CoinModel
import com.example.cryptoexchangedemo.network.CEDService
import com.example.cryptoexchangedemo.network.mapper.CoinDetailResponseMapper
import com.example.cryptoexchangedemo.network.mapper.CoinListResponseMapper
import javax.inject.Inject

/**
 * Created by Batuhan Duvarci on 27.11.2021.
 */
class CoinListRemoteRepositoryImpl @Inject constructor(
    private val cedService: CEDService,
    private val listResponseMapper: CoinListResponseMapper,
    private val detailResponseMapper: CoinDetailResponseMapper
) : CoinListRemoteRepository {

    override suspend fun getCoinList(): List<CoinModel> {
        return listResponseMapper.fromEntityList(cedService.getCoinList().coinList)
    }

    override suspend fun getCoin(coinId: String): CoinDetailModel {
        return detailResponseMapper.mapToDomainModel(cedService.getCoin(coinId))
    }
}