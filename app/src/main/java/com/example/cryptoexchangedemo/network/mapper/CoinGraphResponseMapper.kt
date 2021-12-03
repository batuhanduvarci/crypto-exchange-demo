package com.example.cryptoexchangedemo.network.mapper

import com.example.cryptoexchangedemo.domain.models.CoinGraphModel
import com.example.cryptoexchangedemo.domain.util.DomainMapper
import com.example.cryptoexchangedemo.network.models.CoinGraphItemResponseModel

class CoinGraphResponseMapper: DomainMapper<CoinGraphItemResponseModel, CoinGraphModel> {

    override fun mapToDomainModel(response: CoinGraphItemResponseModel): CoinGraphModel {
        return CoinGraphModel(
            response.d?.toFloat(),
            response.c?.toFloat()
        )
    }

    override fun fromEntityList(initial: List<CoinGraphItemResponseModel>): List<CoinGraphModel> {
        return initial.map { mapToDomainModel(it) }
    }
}