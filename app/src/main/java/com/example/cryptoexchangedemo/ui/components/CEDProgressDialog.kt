package com.example.cryptoexchangedemo.ui.components

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.cryptoexchangedemo.R

class CEDProgressDialog: DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.let {
            it.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.setCancelable(false)
        }

        return inflater.inflate(R.layout.fragment_progress_dialog, container, false)
    }

    companion object {
        fun newInstance() = CEDProgressDialog()
    }
}