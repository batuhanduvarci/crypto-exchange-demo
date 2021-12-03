package com.example.cryptoexchangedemo.ui.coindetail

import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.cryptoexchangedemo.R
import com.example.cryptoexchangedemo.constants.ApplicationConstants
import com.example.cryptoexchangedemo.database.handler.DatabaseResult
import com.example.cryptoexchangedemo.databinding.FragmentCoinDetailBinding
import com.example.cryptoexchangedemo.domain.models.CoinDetailModel
import com.example.cryptoexchangedemo.domain.models.CoinEntityModel
import com.example.cryptoexchangedemo.network.handler.NetworkResult
import com.example.cryptoexchangedemo.ui.SharedViewModel
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

    override val viewModel: CoinDetailViewModel by viewModels()

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val navArgs: CoinDetailFragmentArgs by navArgs()
    private var coinDetailModel: CoinDetailModel? = null
    override var onBackPressedCallback: OnBackPressedCallback? =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(CoinDetailFragmentDirections.toFragmentCoinList())
            }
        }

    override fun bind(view: View) = FragmentCoinDetailBinding.bind(view)

    override fun initUserInterface() {
        with(binding!!) {
            toolbar.titleTextView.text = navArgs.coinId
            updateFavoriteIcon()
            coinDetailModel?.let {
                val titleArray = resources.getStringArray(R.array.selectable_parameter_array)
                lastContainer.titleTextView.text = titleArray[0]
                lastContainer.informationTextView.text = it.las
                buyContainer.titleTextView.text = titleArray[3]
                buyContainer.informationTextView.text = it.buy
                sellContainer.titleTextView.text = titleArray[4]
                sellContainer.informationTextView.text = it.sel
                lowContainer.titleTextView.text = titleArray[1]
                lowContainer.informationTextView.text = it.low
                highContainer.titleTextView.text = titleArray[2]
                highContainer.informationTextView.text = it.hig
                differenceContainer.titleTextView.text = titleArray[5]
                differenceContainer.informationTextView.text = it.ddi
                differencePercentageContainer.titleTextView.text = titleArray[6]
                differencePercentageContainer.informationTextView.text = it.pdd
            }
            toolbar.backImageButton.setOnClickListener {
                findNavController().navigate(CoinDetailFragmentDirections.toFragmentCoinList())
            }

            toolbar.favoriteImageButton.setOnClickListener {
                with(navArgs.coinId) {
                    if (sharedViewModel.favoriteCoinList.contains(CoinEntityModel(navArgs.coinId))) {
                        viewModel.removeFromFavorites(this)
                        return@setOnClickListener
                    }
                    viewModel.addToFavorites(navArgs.coinId)
                }
            }
        }
    }

    override fun initObservers() {
        viewModel.coin.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Success -> {
                    hideLoading()
                    coinDetailModel = it.data
                    initUserInterface()
                }
                is NetworkResult.Loading -> showLoading()
                is NetworkResult.Error -> {
                    hideLoading()
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        viewModel.databaseOperation.observe(viewLifecycleOwner) {
            when (it) {
                is DatabaseResult.Success -> {
                    hideLoading()
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
                is DatabaseResult.Loading -> showLoading()
                is DatabaseResult.Error -> {
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

    private fun updateFavoriteIcon() {
        with(binding!!) {
            if (sharedViewModel.favoriteCoinList.contains(CoinEntityModel(navArgs.coinId))) {
                toolbar.favoriteImageButton.setImageResource(R.drawable.ic_star_filled)
            } else {
                toolbar.favoriteImageButton.setImageResource(R.drawable.ic_star_non_filled)
            }
        }
    }
}