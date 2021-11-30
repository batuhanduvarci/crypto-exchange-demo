package com.example.cryptoexchangedemo.ui.components.extensions

import androidx.fragment.app.Fragment
import com.example.cryptoexchangedemo.ui.components.CEDProgressDialog

fun Fragment.showLoading(){
    val progressDialogFragment = CEDProgressDialog.newInstance()

    childFragmentManager.beginTransaction()
        .addToBackStack(null)

    progressDialogFragment.show(childFragmentManager, "progressDialog")
}

fun Fragment.hideLoading(){
    val progressDialogFragment =
        childFragmentManager.findFragmentByTag("progressDialog") as CEDProgressDialog?
    progressDialogFragment?.dismiss()
}