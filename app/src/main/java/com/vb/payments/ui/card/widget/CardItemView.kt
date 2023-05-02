package com.vb.payments.ui.card.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.vb.payments.R
import com.vb.payments.data.database.card.Card
import com.vb.payments.databinding.ItemViewCardBinding


class CardItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding =
        ItemViewCardBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        layoutParams = binding.root.layoutParams
    }

    fun setItem(card: Card) {
        binding.cardItemCardCvv.text = card.cvv.toString()
        binding.cardItemCardExpiration.text = card.expirationDate
        binding.cardItemCardType.text = card.type
        binding.cardItemCardNumber.text = card.number
    }

    override fun setSelected(isSelected: Boolean) {
        if (this.isSelected != isSelected) {
            if (isSelected) {
                binding.cardItemContainer.setBackgroundColor(context.getColor(R.color.blue_50))
            } else {
                binding.cardItemContainer.background = null
            }
        }
        super.setSelected(isSelected)
    }
}
