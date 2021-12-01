package com.example.cryptoexchangedemo.ui.coindetail

import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.navigation.fragment.navArgs
import com.example.cryptoexchangedemo.R
import com.example.cryptoexchangedemo.constants.ApplicationConstants
import com.example.cryptoexchangedemo.databinding.FragmentCoinDetailBinding
import com.example.cryptoexchangedemo.domain.models.CoinDetailModel
import com.example.cryptoexchangedemo.network.handler.NetworkResult
import com.example.cryptoexchangedemo.ui.base.BaseFragment
import com.example.cryptoexchangedemo.ui.components.extensions.hideLoading
import com.example.cryptoexchangedemo.ui.components.extensions.showLoading
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

/**
 * Created by Batuhan Duvarci on 1.12.2021.
 */
@AndroidEntryPoint
class CoinDetailFragment :
    BaseFragment<FragmentCoinDetailBinding, CoinDetailViewModel>(R.layout.fragment_coin_detail) {

    override val viewModel: CoinDetailViewModel by activityViewModels()

    private val navArgs: CoinDetailFragmentArgs by navArgs()

    private var coinDetailModel: CoinDetailModel? = null

    override fun bind(view: View) = FragmentCoinDetailBinding.bind(view)

    override fun initUserInterface() {
        with(binding!!) {
            toolbar.titleTextView.text = navArgs.coinId
            coinDetailModel?.let {
                lastTextView.text = it.las
                buyTextView.text = it.buy
                sellTextView.text = it.sel
                lowTextView.text = it.low
                highTextView.text = it.hig
                differenceTextView.text = it.ddi
                differencePercentageTextView.text = it.pdd
            }
        }
    }

    override fun initObservers() {
        viewModel.coin.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Success -> {
                    coinDetailModel = it.data
                    hideLoading()
                    initUserInterface()
                }
                is NetworkResult.Loading -> showLoading()
                is NetworkResult.Error -> {
                    hideLoading()
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun startCoroutine() {
        with(viewLifecycleOwner) {
            lifecycleScope.launch(Dispatchers.IO) {
                whenStarted {
                    while (isActive) {
                        viewModel.getCoinDetail(navArgs.coinId)
                        delay(ApplicationConstants.DELAY_INTERVAL_MILLISECONDS)
                    }
                }
            }
        }
    }
}