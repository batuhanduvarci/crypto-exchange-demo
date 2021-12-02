package com.example.cryptoexchangedemo.ui.favorites

import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.cryptoexchangedemo.R
import com.example.cryptoexchangedemo.constants.ApplicationConstants
import com.example.cryptoexchangedemo.databinding.FragmentCoinListBinding
import com.example.cryptoexchangedemo.domain.models.CoinModel
import com.example.cryptoexchangedemo.network.handler.NetworkResult
import com.example.cryptoexchangedemo.ui.SharedViewModel
import com.example.cryptoexchangedemo.ui.base.BaseFragment
import com.example.cryptoexchangedemo.ui.base.OnItemClickCallback
import com.example.cryptoexchangedemo.ui.coinlist.CoinListAdapter
import com.example.cryptoexchangedemo.ui.components.extensions.hideLoading
import com.example.cryptoexchangedemo.ui.components.extensions.showLoading
import com.example.cryptoexchangedemo.ui.util.RecyclerViewItemChangeAnimation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

/**
 * Created by Batuhan Duvarci on 26.11.2021.
 */
@AndroidEntryPoint
class FavoritesFragment :
    BaseFragment<FragmentCoinListBinding, FavoritesViewModel>(R.layout.fragment_coin_list) {

    override val viewModel: FavoritesViewModel by activityViewModels()

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private lateinit var adapter: CoinListAdapter

    override fun bind(view: View) = FragmentCoinListBinding.bind(view)

    override fun initUserInterface() {
        with(binding!!) {
            coinListHeader.coinFirstSelectableSpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        adapter.firstSelectableFieldPosition = p2
                        adapter.notifyDataSetChanged()
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) = Unit

                }

            coinListHeader.coinSecondSelectableSpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        adapter.secondSelectableFieldPosition = p2
                        adapter.notifyDataSetChanged()
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) = Unit

                }

            coinListHeader.coinFirstSelectableSpinner.setSelection(viewModel.firstSelectable)
            coinListHeader.coinSecondSelectableSpinner.setSelection(viewModel.secondSelectable)

            adapter = CoinListAdapter(
                onItemClickCallback = object : OnItemClickCallback<CoinModel> {
                    override fun onItemClick(item: CoinModel) {
                        item.id?.let {
                            findNavController().navigate(
                                FavoritesFragmentDirections.toFragmentCoinDetail(
                                    it
                                )
                            )
                        }
                    }
                },
                firstSelectableFieldPosition = viewModel.firstSelectable,
                secondSelectableFieldPosition = viewModel.secondSelectable
            )
            coinListRecyclerView.adapter = adapter
            coinListRecyclerView.itemAnimator = RecyclerViewItemChangeAnimation()
        }
    }

    override fun initObservers() {
        viewModel.coinList.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Success -> {
                    hideLoading()
                    adapter.submitList(it.data)
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
        with(viewLifecycleOwner){
            lifecycleScope.launchWhenStarted {
                while (isActive) {
                    viewModel.getCoinList(sharedViewModel.favoriteCoinList)
                    delay(ApplicationConstants.DELAY_INTERVAL_MILLISECONDS)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        with(binding!!) {
            viewModel.firstSelectable =
                coinListHeader.coinFirstSelectableSpinner.selectedItemPosition
            viewModel.secondSelectable =
                coinListHeader.coinSecondSelectableSpinner.selectedItemPosition
        }
    }
}