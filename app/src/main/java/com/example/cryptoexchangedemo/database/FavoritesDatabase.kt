package com.example.cryptoexchangedemo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cryptoexchangedemo.domain.models.CoinEntityModel

/**
 * Created by Batuhan Duvarci on 30.11.2021.
 */
@Database(entities = [CoinEntityModel::class], version = 1, exportSchema = false)
abstract class FavoritesDatabase: RoomDatabase() {

    abstract fun favoritesDao(): FavoritesDao
}