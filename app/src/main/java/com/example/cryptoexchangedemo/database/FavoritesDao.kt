package com.example.cryptoexchangedemo.database

import androidx.room.*
import com.example.cryptoexchangedemo.domain.models.CoinEntityModel
import kotlinx.coroutines.flow.Flow

/**
 * Created by Batuhan Duvarci on 30.11.2021.
 */
@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(coin: CoinEntityModel)

    @Delete
    suspend fun delete(coin: CoinEntityModel)

    @Query("SELECT * FROM favorites")
    fun getFavoriteCoins(): Flow<List<CoinEntityModel>>
}