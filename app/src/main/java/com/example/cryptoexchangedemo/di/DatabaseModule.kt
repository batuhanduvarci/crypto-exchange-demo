package com.example.cryptoexchangedemo.di

import android.app.Application
import androidx.room.Room
import com.example.cryptoexchangedemo.database.FavoritesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Batuhan Duvarci on 30.11.2021.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun databaseProvider(app: Application) =
        Room.databaseBuilder(app, FavoritesDatabase::class.java, "favoritesDB")
            .fallbackToDestructiveMigration().build()

    @Provides
    fun daoProvider(database: FavoritesDatabase) = database.favoritesDao()
}