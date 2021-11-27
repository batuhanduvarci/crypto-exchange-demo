package com.example.cryptoexchangedemo.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Batuhan Duvarci on 27.11.2021.
 */
@Parcelize
data class CoinModel(
    val definition: String? = null,
    val dailyHighestPrice: String? = null,
    val updated: String? = null,
    val buy: String? = null,
    val difference: String? = null,
    val cl3: String? = null,
    val lastDayPrice: String? = null,
    val id: String? = null,
    val momentaryData: Boolean? = null,
    val differencePercentage: String? = null,
    val dailyLowestPrice: String? = null,
    val coinCode: String? = null,
    val sell: String? = null,
    val last: String? = null
): Parcelable