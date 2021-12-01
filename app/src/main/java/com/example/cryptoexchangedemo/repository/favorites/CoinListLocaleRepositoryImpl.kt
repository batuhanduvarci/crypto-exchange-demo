package com.example.cryptoexchangedemo.repository.favorites

import com.example.cryptoexchangedemo.database.FavoritesDao
import com.example.cryptoexchangedemo.domain.models.CoinEntityModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Batuhan Duvarci on 30.11.2021.
 */
class CoinListLocaleRepositoryImpl @Inject constructor(private val favoritesDao: FavoritesDao): CoinListLocaleRepository {

    override suspend fun getCoinList(): Flow<List<CoinEntityModel>> {
        return favoritesDao.getFavoriteCoins()
    }

    override suspend fun insertCoinId(coinId: String) {
        favoritesDao.add(CoinEntityModel(coinId))
    }

    override suspend fun removeCoinId(coin: CoinEntityModel) {
        favoritesDao.delete(coin)
    }

}