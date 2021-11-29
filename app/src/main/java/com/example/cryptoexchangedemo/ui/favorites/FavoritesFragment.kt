package com.example.cryptoexchangedemo.ui.favorites

import android.view.View
import androidx.fragment.app.viewModels
import com.example.cryptoexchangedemo.R
import com.example.cryptoexchangedemo.databinding.FragmentCoinListBinding
import com.example.cryptoexchangedemo.ui.base.BaseFragment

/**
 * Created by Batuhan Duvarci on 26.11.2021.
 */
class FavoritesFragment :
    BaseFragment<FragmentCoinListBinding, FavoritesViewModel>(R.layout.fragment_coin_list) {

    override val viewModel: FavoritesViewModel by viewModels()

    override fun bind(view: View) = FragmentCoinListBinding.bind(view)

    override fun initUserInterface() {
        //TODO("Not yet implemented")
    }

    override fun initObservers() {
        //TODO("Not yet implemented")
    }

    override fun startCoroutine() {
        //TODO("Not yet implemented")
    }
}