package com.example.cryptoexchangedemo.network.mapper

import com.example.cryptoexchangedemo.domain.models.CoinModel
import com.example.cryptoexchangedemo.domain.util.DomainMapper
import com.example.cryptoexchangedemo.network.models.CoinListItemResponseModel

/**
 * Created by Batuhan Duvarci on 27.11.2021.
 */
class CoinListResponseMapper : DomainMapper<CoinListItemResponseModel, CoinModel> {

    override fun mapToDomainModel(response: CoinListItemResponseModel): CoinModel {
        return CoinModel(
            response.def,
            response.hig,
            response.clo,
            response.buy,
            response.ddi,
            response.cl3,
            response.pdc,
            response.tke,
            response.rtp,
            response.pdd,
            response.low,
            response.cod,
            response.sel,
            response.las
        )
    }

    override fun fromEntityList(initial: List<CoinListItemResponseModel>): List<CoinModel> {
        return initial.map { mapToDomainModel(it) }
    }
}