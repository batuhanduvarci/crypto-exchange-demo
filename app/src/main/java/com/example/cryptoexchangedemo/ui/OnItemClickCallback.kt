package com.example.cryptoexchangedemo.ui

import com.example.cryptoexchangedemo.domain.models.CoinModel

/**
 * Created by Batuhan Duvarci on 27.11.2021.
 */
interface OnItemClickCallback<T> {
    fun onItemClick(item: T)
}