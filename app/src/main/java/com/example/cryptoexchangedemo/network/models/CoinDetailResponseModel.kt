package com.example.cryptoexchangedemo.network.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Batuhan Duvarci on 30.11.2021.
 */
data class CoinDetailResponseModel(
    @SerializedName("d")
    var d: List<CoinDetailItemResponseModel> = listOf()
)
