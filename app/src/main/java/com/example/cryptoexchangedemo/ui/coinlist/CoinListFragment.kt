package com.example.cryptoexchangedemo.ui.coinlist

import android.view.View
import androidx.fragment.app.viewModels
import com.example.cryptoexchangedemo.R
import com.example.cryptoexchangedemo.databinding.FragmentCoinListBinding
import com.example.cryptoexchangedemo.ui.BaseFragment

/**
 * Created by Batuhan Duvarci on 26.11.2021.
 */
class CoinListFragment : BaseFragment<FragmentCoinListBinding, CoinListViewModel>(R.layout.fragment_coin_list) {

    override val viewModel: CoinListViewModel by viewModels()

    override fun bind(view: View) = FragmentCoinListBinding.bind(view)

    override fun initUserInterface() {
        //TODO("Not yet implemented")
    }

    override fun initObservers() {
        //TODO("Not yet implemented")
    }
}