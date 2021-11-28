package com.example.cryptoexchangedemo.ui.coinlist

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.example.cryptoexchangedemo.R
import com.example.cryptoexchangedemo.databinding.ItemCoinListBinding
import com.example.cryptoexchangedemo.domain.models.CoinModel
import com.example.cryptoexchangedemo.ui.base.CEDBaseListAdapter

/**
 * Created by Batuhan Duvarci on 28.11.2021.
 */
class CoinListAdapter(diffUtil: DiffUtil.ItemCallback<CoinModel> = object : DiffUtil.ItemCallback<CoinModel>() {
    override fun areItemsTheSame(oldItem: CoinModel, newItem: CoinModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CoinModel, newItem: CoinModel): Boolean {
        return oldItem == newItem
    }
}): CEDBaseListAdapter<CoinModel>(R.layout.item_coin_list, diffUtilCallback = diffUtil) {

    override fun bind(itemView: View, item: CoinModel, position: Int, viewHolder: ViewHolderImpl) {
        val binding = ItemCoinListBinding.bind(itemView)
        with(binding){
            coinCodeTextView.text = item.coinCode
            coinUpdatedTextView.text = item.updated
            coinDefinitionTextView.text = item.definition
        }

    }
}