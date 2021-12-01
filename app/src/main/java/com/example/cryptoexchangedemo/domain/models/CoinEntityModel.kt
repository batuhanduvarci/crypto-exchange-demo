package com.example.cryptoexchangedemo.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Batuhan Duvarci on 30.11.2021.
 */
@Entity(tableName = "favorites")
data class CoinEntityModel(
    @PrimaryKey(autoGenerate = false) val coinId: String
)