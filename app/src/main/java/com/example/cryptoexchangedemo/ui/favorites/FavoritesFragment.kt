package com.example.cryptoexchangedemo.ui.favorites

import android.view.View
import androidx.fragment.app.viewModels
import com.example.cryptoexchangedemo.R
import com.example.cryptoexchangedemo.databinding.FragmentFavoritesBinding
import com.example.cryptoexchangedemo.ui.BaseFragment

/**
 * Created by Batuhan Duvarci on 26.11.2021.
 */
class FavoritesFragment : BaseFragment<FragmentFavoritesBinding, FavoritesViewModel>(R.layout.fragment_favorites) {

    override val viewModel: FavoritesViewModel by viewModels()

    override fun bind(view: View) = FragmentFavoritesBinding.bind(view)

    override fun initUserInterface() {
        //TODO("Not yet implemented")
    }

    override fun initObservers() {
        //TODO("Not yet implemented")
    }
}