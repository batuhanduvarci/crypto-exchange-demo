package com.example.cryptoexchangedemo.ui.coinlist

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import com.example.cryptoexchangedemo.R
import com.example.cryptoexchangedemo.databinding.FragmentCoinListBinding
import com.example.cryptoexchangedemo.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

/**
 * Created by Batuhan Duvarci on 26.11.2021.
 */
@AndroidEntryPoint
class CoinListFragment : BaseFragment<FragmentCoinListBinding, CoinListViewModel>(R.layout.fragment_coin_list) {

    override val viewModel: CoinListViewModel by activityViewModels()

    private lateinit var adapter: CoinListAdapter

    override fun bind(view: View) = FragmentCoinListBinding.bind(view)

    override fun initUserInterface() {
        with(binding!!){
            adapter = CoinListAdapter()
            coinListRecyclerView.adapter = adapter
        }
    }

    override fun initObservers() {
        viewModel.coinList.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }

    override fun startCoroutine(){
        with(viewLifecycleOwner){
            lifecycleScope.launch(Dispatchers.IO) {
                whenStarted {
                    while (isActive){
                        viewModel.getCoinList()
                        delay(2000)
                    }
                }
            }
        }
    }
}