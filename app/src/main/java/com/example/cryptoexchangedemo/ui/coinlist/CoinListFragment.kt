package com.example.cryptoexchangedemo.ui.coinlist

import android.view.View
import android.widget.AdapterView
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
            coinListHeader.coinFirstSelectableSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    adapter.firstSelectableFieldPosition = p2
                    adapter.notifyDataSetChanged()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) = Unit

            }

            coinListHeader.coinSecondSelectableSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    adapter.secondSelectableFieldPosition = p2
                    adapter.notifyDataSetChanged()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) = Unit

            }

            coinListHeader.coinFirstSelectableSpinner.setSelection(viewModel.firstSelectable)
            coinListHeader.coinSecondSelectableSpinner.setSelection(viewModel.secondSelectable)

            adapter = CoinListAdapter(firstSelectableFieldPosition = viewModel.firstSelectable, secondSelectableFieldPosition = viewModel.secondSelectable)
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

    override fun onPause() {
        super.onPause()
        with(binding!!){
            viewModel.firstSelectable = coinListHeader.coinFirstSelectableSpinner.selectedItemPosition
            viewModel.secondSelectable = coinListHeader.coinSecondSelectableSpinner.selectedItemPosition
        }
    }
}