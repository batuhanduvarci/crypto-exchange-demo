package com.example.cryptoexchangedemo.ui.coinlist

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.example.cryptoexchangedemo.R
import com.example.cryptoexchangedemo.databinding.ItemCoinListBinding
import com.example.cryptoexchangedemo.domain.models.CoinModel
import com.example.cryptoexchangedemo.ui.base.CEDBaseListAdapter
import com.google.android.material.textview.MaterialTextView

/**
 * Created by Batuhan Duvarci on 28.11.2021.
 */
class CoinListAdapter(
    diffUtil: DiffUtil.ItemCallback<CoinModel> = object : DiffUtil.ItemCallback<CoinModel>() {
        override fun areItemsTheSame(oldItem: CoinModel, newItem: CoinModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CoinModel, newItem: CoinModel): Boolean {
            return oldItem == newItem
        }
    }, var firstSelectableFieldPosition: Int, var secondSelectableFieldPosition: Int
) : CEDBaseListAdapter<CoinModel>(R.layout.item_coin_list, diffUtilCallback = diffUtil) {

    override fun bind(itemView: View, item: CoinModel, position: Int, viewHolder: ViewHolderImpl) {
        val binding = ItemCoinListBinding.bind(itemView)
        with(binding) {
            coinCodeTextView.text = item.coinCode
            coinUpdatedTextView.text = item.updated
            coinDefinitionTextView.text = item.definition
            coinDefinitionTextView.isSelected = true
            activateSelectableField(coinFirstSelectableTextView, firstSelectableFieldPosition, item)
            activateSelectableField(coinSecondSelectableTextView, secondSelectableFieldPosition, item)
        }

    }

    private fun activateSelectableField(textView: MaterialTextView, fieldPosition: Int, model: CoinModel) {
        when (fieldPosition) {
            0 -> textView.text = model.last
            1 -> textView.text = model.dailyHighestPrice
            2 -> textView.text = model.dailyLowestPrice
            3 -> textView.text = model.buy
            4 -> textView.text = model.sell
            5 -> textView.text = model.difference
            6 -> textView.text = model.differencePercentage
            7 -> textView.text = model.lastDayPrice
        }
    }
}