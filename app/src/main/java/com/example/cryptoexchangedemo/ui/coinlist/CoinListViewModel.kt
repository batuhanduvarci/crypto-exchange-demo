package com.example.cryptoexchangedemo.ui.coinlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoexchangedemo.domain.models.CoinModel
import com.example.cryptoexchangedemo.repository.coinlist.CoinListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Batuhan Duvarci on 26.11.2021.
 */
@HiltViewModel
class CoinListViewModel @Inject constructor(private val repository: CoinListRepository): ViewModel() {

    private val _coinList = MutableLiveData<List<CoinModel>>()
    val coinList: MutableLiveData<List<CoinModel>> get() = _coinList

    init {
        getCoinList()
    }

    fun getCoinList() = viewModelScope.launch(Dispatchers.IO) {
        _coinList.postValue(repository.getCoinList())
    }
}