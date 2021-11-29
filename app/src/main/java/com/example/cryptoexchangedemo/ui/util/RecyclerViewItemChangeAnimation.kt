package com.example.cryptoexchangedemo.ui.util

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoexchangedemo.R
import com.example.cryptoexchangedemo.ui.base.CEDBaseViewHolder

/**
 * Created by Batuhan Duvarci on 29.11.2021.
 */
class RecyclerViewItemChangeAnimation : DefaultItemAnimator() {

    override fun canReuseUpdatedViewHolder(viewHolder: RecyclerView.ViewHolder) = true

    override fun recordPreLayoutInformation(
        state: RecyclerView.State,
        viewHolder: RecyclerView.ViewHolder,
        changeFlags: Int,
        payloads: MutableList<Any>
    ): ItemHolderInfo {
        when (changeFlags) {
            FLAG_CHANGED -> {
                for (payload in payloads) {
                    return CoinItemHolderInfo(payload as? AnimationEnum)
                }
            }
            else -> Unit
        }
        return super.recordPreLayoutInformation(state, viewHolder, changeFlags, payloads)
    }

    override fun animateChange(
        oldHolder: RecyclerView.ViewHolder,
        newHolder: RecyclerView.ViewHolder,
        preInfo: ItemHolderInfo,
        postInfo: ItemHolderInfo
    ): Boolean {
        val viewHolder = newHolder as CEDBaseViewHolder<*>

        if (preInfo is CoinItemHolderInfo) {
            when (preInfo.state) {
                AnimationEnum.INCREASE -> {
                    colorAnimation(viewHolder, R.color.ced_green)
                }
                AnimationEnum.DECREASE -> {
                    colorAnimation(viewHolder, R.color.ced_red)
                }
            }
            return true
        }

        return super.animateChange(oldHolder, newHolder, preInfo, postInfo)
    }

    private fun colorAnimation(viewHolder: CEDBaseViewHolder<*>, colorStart: Int) {
        val colorFrom: Int = ContextCompat.getColor(viewHolder.itemView.context, R.color.white)
        val colorTo: Int = ContextCompat.getColor(viewHolder.itemView.context, colorStart)
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo, colorFrom)
        colorAnimation.duration = 1000 // milliseconds
        colorAnimation.addUpdateListener { animator ->
            viewHolder.itemView.findViewById<ConstraintLayout>(R.id.container).setBackgroundColor(
                animator.animatedValue as Int
            )
        }
        colorAnimation.start()
    }

    class CoinItemHolderInfo(val state: AnimationEnum?) : ItemHolderInfo()
}