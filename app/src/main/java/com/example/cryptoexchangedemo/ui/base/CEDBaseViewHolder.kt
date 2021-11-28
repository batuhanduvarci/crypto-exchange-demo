package com.example.cryptoexchangedemo.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Batuhan Duvarci on 27.11.2021.
 */
abstract class CEDBaseViewHolder<T>(view: View): RecyclerView.ViewHolder(view) {
    abstract fun bind(item: T)
}