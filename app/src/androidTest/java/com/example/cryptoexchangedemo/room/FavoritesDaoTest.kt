package com.example.cryptoexchangedemo.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.cryptoexchangedemo.database.FavoritesDao
import com.example.cryptoexchangedemo.database.FavoritesDatabase
import com.example.cryptoexchangedemo.domain.models.CoinEntityModel
import com.example.cryptoexchangedemo.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class FavoritesDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: FavoritesDatabase
    private lateinit var dao: FavoritesDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            FavoritesDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.favoritesDao()
    }

    @Test
    fun database_addFavorite_test() = runBlocking {
        val favoriteModel = CoinEntityModel(coinId = "testId")
        dao.add(favoriteModel)

        val favoriteCoins = dao.getFavoriteCoins().asLiveData().getOrAwaitValue()

        assertThat(favoriteCoins).contains(favoriteModel)
    }

    @Test
    fun database_deleteFavorite_test() = runBlocking {
        val favoriteModel = CoinEntityModel(coinId = "testId")
        dao.add(favoriteModel)
        dao.delete(favoriteModel)

        val favoriteCoins = dao.getFavoriteCoins().asLiveData().getOrAwaitValue()

        assertThat(favoriteCoins).doesNotContain(favoriteModel)
    }

    @After
    fun teardown(){
        database.close()
    }
}