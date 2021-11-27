package com.example.cryptoexchangedemo.network.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Batuhan Duvarci on 27.11.2021.
 */
data class CoinListItemResponseModel(
    @SerializedName("def")
    var def: String? = null,
    @SerializedName("hig")
    var hig: String? = null,
    @SerializedName("clo")
    var clo: String? = null,
    @SerializedName("buy")
    var buy: String? = null,
    @SerializedName("ddi")
    var ddi: String? = null,
    @SerializedName("cl3")
    var cl3: String? = null,
    @SerializedName("pdc")
    var pdc: String? = null,
    @SerializedName("tke")
    var tke: String? = null,
    @SerializedName("rtp")
    var rtp: Boolean? = null,
    @SerializedName("pdd")
    var pdd: String? = null,
    @SerializedName("low")
    var low: String? = null,
    @SerializedName("cod")
    var cod: String? = null,
    @SerializedName("sel")
    var sel: String? = null,
    @SerializedName("las")
    var las: String? = null
)
