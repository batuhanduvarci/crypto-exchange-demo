package com.example.cryptoexchangedemo.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

/**
 * Created by Batuhan Duvarci on 27.11.2021.
 */
abstract class CEDBaseListAdapter<T>(
    private val layoutRes: Int = -1,
    private val onItemClickCallback: OnItemClickCallback<T>? = null,
    diffUtilCallback: DiffUtil.ItemCallback<T>
): ListAdapter<T, CEDBaseViewHolder<T>>(diffUtilCallback) {

    abstract fun bind(itemView: View, item: T, position: Int, viewHolder: ViewHolderImpl)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CEDBaseViewHolder<T> {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return ViewHolderImpl(view)
    }

    override fun getItemViewType(position: Int): Int {
        return layoutRes
    }

    override fun onBindViewHolder(holder: CEDBaseViewHolder<T>, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolderImpl(itemView: View) : CEDBaseViewHolder<T>(itemView) {
        override fun bind(item: T) {
            this@CEDBaseListAdapter.bind(itemView, item, position, this)
            onItemClickCallback?.let { it ->
                itemView.setOnClickListener { _ ->
                    it.onItemClick(item)
                }
            }
        }
    }
}