package com.vb.payments.ui.card.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.vb.payments.databinding.ProgressScreenBinding

class ProgressScreen @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding =
        ProgressScreenBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        layoutParams = binding.root.layoutParams
        isVisible = false
    }

    fun setProgress(isProgress: Boolean) {
        isVisible = isProgress
    }
}
