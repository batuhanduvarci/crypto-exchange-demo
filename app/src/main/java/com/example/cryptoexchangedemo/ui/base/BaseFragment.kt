package com.example.cryptoexchangedemo.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

/**
 * Created by Batuhan Duvarci on 26.11.2021.
 */
abstract class BaseFragment<VB: ViewBinding, VM: ViewModel>(layoutId: Int): Fragment(layoutId) {

    abstract val viewModel: VM

    open var binding: VB? = null

    abstract fun bind(view: View) : VB

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = bind(view)

        initUserInterface()
        initObservers()
    }

    abstract fun initUserInterface()

    abstract fun initObservers()

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}