package com.example.cryptoexchangedemo.ui.coinlist

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptoexchangedemo.R
import com.example.cryptoexchangedemo.domain.models.CoinModel
import com.example.cryptoexchangedemo.network.handler.NetworkResult
import com.example.cryptoexchangedemo.repository.coinlist.CoinListRemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Batuhan Duvarci on 26.11.2021.
 */
@HiltViewModel
class CoinListViewModel @Inject constructor(private val remoteRepository: CoinListRemoteRepository, private val resources: Resources) :
    ViewModel() {

    var firstSelectable: Int = 0
    var secondSelectable: Int = 0

    private val _coinList = MutableLiveData<NetworkResult<List<CoinModel>>>(NetworkResult.Loading())
    val coinList: MutableLiveData<NetworkResult<List<CoinModel>>> get() = _coinList

    suspend fun getCoinList() {
        try {
            val response = remoteRepository.getCoinList()
            if (response.isEmpty()) {
                _coinList.postValue(NetworkResult.Error(resources.getString(R.string.generic_network_error_text)))
            }else{
                _coinList.postValue(NetworkResult.Success(response))
            }
        } catch (ex: Exception) {
            _coinList.postValue(NetworkResult.Error(ex.message))
        }

    }

}