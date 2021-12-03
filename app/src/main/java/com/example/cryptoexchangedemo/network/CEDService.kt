package com.example.cryptoexchangedemo.network

import com.example.cryptoexchangedemo.constants.ApplicationConstants
import com.example.cryptoexchangedemo.network.models.CoinDetailItemResponseModel
import com.example.cryptoexchangedemo.network.models.CoinDetailResponseModel
import com.example.cryptoexchangedemo.network.models.CoinGraphItemResponseModel
import com.example.cryptoexchangedemo.network.models.CoinListResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Batuhan Duvarci on 27.11.2021.
 */
interface CEDService {

    @GET(ApplicationConstants.COIN_LIST_ENDPOINT)
    suspend fun getCoinList(): CoinListResponseModel

    @GET(ApplicationConstants.COIN_DETAIL_ENDPOINT)
    suspend fun getCoin(@Query("cod") coinId: String): CoinDetailResponseModel

    @GET(ApplicationConstants.COIN_GRAPH_ENDPOINT)
    suspend fun getCoinGraph(@Query("cod") coinId: String): List<CoinGraphItemResponseModel>
}