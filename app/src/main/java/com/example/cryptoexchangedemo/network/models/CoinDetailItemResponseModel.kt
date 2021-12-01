package com.example.cryptoexchangedemo.network.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Batuhan Duvarci on 1.12.2021.
 */
data class CoinDetailItemResponseModel(
    @SerializedName("desc")
    var desc: String? = null,
    @SerializedName("type")
    var type: Int? = null,
    @SerializedName("clo")
    var clo: String? = null,
    @SerializedName("fields")
    var fields: CoinDetailFieldsResponseModel? = null

)
