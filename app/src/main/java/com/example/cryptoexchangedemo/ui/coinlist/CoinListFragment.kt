package com.example.cryptoexchangedemo.ui.coinlist

import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.navigation.fragment.findNavController
import com.example.cryptoexchangedemo.R
import com.example.cryptoexchangedemo.constants.ApplicationConstants
import com.example.cryptoexchangedemo.databinding.FragmentCoinListBinding
import com.example.cryptoexchangedemo.domain.models.CoinModel
import com.example.cryptoexchangedemo.network.handler.NetworkResult
import com.example.cryptoexchangedemo.ui.OnItemClickCallback
import com.example.cryptoexchangedemo.ui.base.BaseFragment
import com.example.cryptoexchangedemo.ui.components.extensions.hideLoading
import com.example.cryptoexchangedemo.ui.components.extensions.showLoading
import com.example.cryptoexchangedemo.ui.util.RecyclerViewItemChangeAnimation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

/**
 * Created by Batuhan Duvarci on 26.11.2021.
 */
@AndroidEntryPoint
class CoinListFragment :
    BaseFragment<FragmentCoinListBinding, CoinListViewModel>(R.layout.fragment_coin_list) {

    override val viewModel: CoinListViewModel by activityViewModels()

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
                                CoinListFragmentDirections.toFragmentCoinDetail(
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
        with(viewLifecycleOwner) {
            lifecycleScope.launch(Dispatchers.IO) {
                whenStarted {
                    while (isActive) {
                        viewModel.getCoinList()
                        delay(ApplicationConstants.DELAY_INTERVAL_MILLISECONDS)
                    }
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