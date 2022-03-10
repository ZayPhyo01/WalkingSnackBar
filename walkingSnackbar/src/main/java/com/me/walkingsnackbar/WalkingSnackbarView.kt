package com.me.walkingsnackbar

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.me.walkingsnackbar.databinding.DefaultSnackBarBinding

internal class WalkingSnackbarView (c: Context, attributeSet: AttributeSet) : LinearLayout(c, attributeSet) {
    var binding: DefaultSnackBarBinding? = null

    fun initView(view: WalkingSnackbarView) {
        binding = DefaultSnackBarBinding.bind(view)
    }
}