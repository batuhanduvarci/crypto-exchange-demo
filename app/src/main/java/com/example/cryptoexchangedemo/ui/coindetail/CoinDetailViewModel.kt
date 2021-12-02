package com.example.cryptoexchangedemo.ui.coindetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoexchangedemo.database.handler.DatabaseResult
import com.example.cryptoexchangedemo.domain.models.CoinDetailModel
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
    private val localRepository: CoinListLocaleRepository
) :
    ViewModel() {

    private val _coin = MutableLiveData<NetworkResult<CoinDetailModel>>(NetworkResult.Loading())
    val coin: MutableLiveData<NetworkResult<CoinDetailModel>> get() = _coin

    private val _databaseOperation = MutableLiveData<DatabaseResult>(DatabaseResult.Loading())
    val databaseOperation: MutableLiveData<DatabaseResult> get() = _databaseOperation

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

    fun addToFavorites(coinId: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            localRepository.insertCoinId(coinId)
            _databaseOperation.postValue(DatabaseResult.Success("The coin is added to favorites successfully"))
        } catch (ex: Exception) {
            _databaseOperation.postValue(DatabaseResult.Error("An error occurred while adding the coin to favorites!"))
        }
    }

    fun removeFromFavorites(coinId: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            localRepository.removeCoinId(coinId)
            _databaseOperation.postValue(DatabaseResult.Success("The coin is removed from favorites successfully"))
        } catch (ex: Exception) {
            _databaseOperation.postValue(DatabaseResult.Error("An error occurred while removing the coin from favorites!"))
        }
    }
}