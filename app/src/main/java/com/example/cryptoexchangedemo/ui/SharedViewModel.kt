package com.example.cryptoexchangedemo.ui

import androidx.lifecycle.ViewModel
import com.example.cryptoexchangedemo.domain.models.CoinEntityModel
import com.example.cryptoexchangedemo.repository.favorites.CoinListLocaleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val localeRepository: CoinListLocaleRepository
) : ViewModel() {

    var favoriteCoinList: List<CoinEntityModel> = listOf()

    suspend fun getFavoriteCoinList() = localeRepository.getCoinList().collect {
        favoriteCoinList = it
    }
}