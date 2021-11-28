package com.example.cryptoexchangedemo.network.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Batuhan Duvarci on 27.11.2021.
 */
data class CoinListResponseModel(
    @SerializedName("l")
    var coinList: List<CoinListItemResponseModel> = listOf()
)