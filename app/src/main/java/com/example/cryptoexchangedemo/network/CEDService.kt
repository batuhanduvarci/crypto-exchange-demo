package com.example.cryptoexchangedemo.network

import com.example.cryptoexchangedemo.constants.ApplicationConstants
import com.example.cryptoexchangedemo.network.models.CoinListResponseModel
import retrofit2.http.GET

/**
 * Created by Batuhan Duvarci on 27.11.2021.
 */
interface CEDService {

    @GET(ApplicationConstants.COIN_LIST_ENDPOINT)
    suspend fun getCoinList(): CoinListResponseModel
}