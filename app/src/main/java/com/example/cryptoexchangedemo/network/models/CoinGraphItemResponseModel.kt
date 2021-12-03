package com.example.cryptoexchangedemo.network.models

import com.google.gson.annotations.SerializedName

data class CoinGraphItemResponseModel(
    @SerializedName("d")
    var d: Long? = null,
    @SerializedName("c")
    var c: Double? = null
)
