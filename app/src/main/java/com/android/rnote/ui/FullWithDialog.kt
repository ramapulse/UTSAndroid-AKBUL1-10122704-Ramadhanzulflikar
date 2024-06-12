package com.android.rnote.ui

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.ViewGroup

class FullWithDialog(context : Context,i: Int) : Dialog(context,i) {
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window?.setGravity(Gravity.CENTER)
    }
}