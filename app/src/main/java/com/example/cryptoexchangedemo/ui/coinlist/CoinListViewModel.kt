package com.example.cryptoexchangedemo.ui.coinlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptoexchangedemo.domain.models.CoinModel
import com.example.cryptoexchangedemo.repository.coinlist.CoinListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Batuhan Duvarci on 26.11.2021.
 */
@HiltViewModel
class CoinListViewModel @Inject constructor(private val repository: CoinListRepository) :
    ViewModel() {

    var firstSelectable: Int = 0
    var secondSelectable: Int = 0

    private val _coinList = MutableLiveData<List<CoinModel>>()
    val coinList: MutableLiveData<List<CoinModel>> get() = _coinList

    suspend fun getCoinList() = _coinList.postValue(repository.getCoinList())

}