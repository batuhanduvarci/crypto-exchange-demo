package com.example.cryptoexchangedemo.ui.coindetail

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoexchangedemo.R
import com.example.cryptoexchangedemo.database.handler.DatabaseResult
import com.example.cryptoexchangedemo.domain.models.CoinDetailModel
import com.example.cryptoexchangedemo.domain.models.CoinGraphModel
import com.example.cryptoexchangedemo.network.handler.NetworkResult
import com.example.cryptoexchangedemo.repository.coinlist.CoinListRemoteRepository
import com.example.cryptoexchangedemo.repository.favorites.CoinListLocaleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Batuhan Duvarci on 1.12.2021.
 */
@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val remoteRepository: CoinListRemoteRepository,
    private val localRepository: CoinListLocaleRepository,
    private val resources: Resources
) :
    ViewModel() {

    private val _coin = MutableLiveData<NetworkResult<CoinDetailModel>>(NetworkResult.Loading())
    val coin: MutableLiveData<NetworkResult<CoinDetailModel>> get() = _coin

    private val _databaseOperation = MutableLiveData<DatabaseResult>(DatabaseResult.Loading())
    val databaseOperation: MutableLiveData<DatabaseResult> get() = _databaseOperation

    private val _coinGraph = MutableLiveData<NetworkResult<List<CoinGraphModel>>>()
    val coinGraph: MutableLiveData<NetworkResult<List<CoinGraphModel>>> get() = _coinGraph

    suspend fun getCoinDetail(coinId: String) {
        try {
            val response = remoteRepository.getCoin(coinId)
            if (response == null) {
                _coin.postValue(NetworkResult.Error("Error"))
            } else {
                _coin.postValue(NetworkResult.Success(response))
                getCoinGraph(coinId)
            }
        } catch (ex: Exception) {
            _coin.postValue(NetworkResult.Error(ex.message))
        }
    }

    private suspend fun getCoinGraph(coinId: String) {
        try {
            val response = remoteRepository.getCoinGraph(coinId)
            if (response.isEmpty()) {
                _coinGraph.postValue(NetworkResult.Error("Error"))
            } else {
                _coinGraph.postValue(NetworkResult.Success(response))
            }
        } catch (ex: Exception) {
            _coinGraph.postValue(NetworkResult.Error(ex.message))
        }
    }

    fun addToFavorites(coinId: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            localRepository.insertCoinId(coinId)
            _databaseOperation.postValue(DatabaseResult.Success(resources.getString(R.string.fragment_coin_details_add_to_favorites_success_text)))
        } catch (ex: Exception) {
            _databaseOperation.postValue(DatabaseResult.Error(resources.getString(R.string.fragment_coin_details_add_to_favorites_error_text)))
        }
    }

    fun removeFromFavorites(coinId: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            localRepository.removeCoinId(coinId)
            _databaseOperation.postValue(DatabaseResult.Success(resources.getString(R.string.fragment_coin_details_remove_from_favorites_success_text)))
        } catch (ex: Exception) {
            _databaseOperation.postValue(DatabaseResult.Error(resources.getString(R.string.fragment_coin_details_remove_from_favorites_error_text)))
        }
    }
}