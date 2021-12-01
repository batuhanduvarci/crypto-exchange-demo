package com.example.cryptoexchangedemo.ui.coindetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptoexchangedemo.domain.models.CoinDetailModel
import com.example.cryptoexchangedemo.network.handler.NetworkResult
import com.example.cryptoexchangedemo.repository.coinlist.CoinListRemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Batuhan Duvarci on 1.12.2021.
 */
@HiltViewModel
class CoinDetailViewModel @Inject constructor(private val remoteRepository: CoinListRemoteRepository) :
    ViewModel() {

    private val _coin = MutableLiveData<NetworkResult<CoinDetailModel>>(NetworkResult.Loading())
    val coin: MutableLiveData<NetworkResult<CoinDetailModel>> get() = _coin

    suspend fun getCoinDetail(coinId: String) {
        try {
            val response = remoteRepository.getCoin(coinId)
            if (response == null) {
                _coin.postValue(NetworkResult.Error("Error"))
            } else {
                _coin.postValue(NetworkResult.Success(response))
            }
        } catch (ex: Exception) {
            _coin.postValue(NetworkResult.Error(ex.message))
        }
    }
}