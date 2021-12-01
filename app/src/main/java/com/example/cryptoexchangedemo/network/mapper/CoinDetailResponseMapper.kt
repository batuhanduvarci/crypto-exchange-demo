package com.example.cryptoexchangedemo.network.mapper

import com.example.cryptoexchangedemo.domain.models.CoinDetailModel
import com.example.cryptoexchangedemo.domain.util.DomainMapper
import com.example.cryptoexchangedemo.network.models.CoinDetailResponseModel

/**
 * Created by Batuhan Duvarci on 30.11.2021.
 */
class CoinDetailResponseMapper : DomainMapper<CoinDetailResponseModel, CoinDetailModel?> {

    override fun mapToDomainModel(response: CoinDetailResponseModel): CoinDetailModel? {
        response.d[0].fields?.let {
            return CoinDetailModel(
                it.buy,
                it.ddi,
                it.hig,
                it.las,
                it.low,
                it.pdd,
                it.sel
            )
        }
        return null
    }

    override fun fromEntityList(initial: List<CoinDetailResponseModel>) = listOf<CoinDetailModel>()
}