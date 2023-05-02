package com.vb.payments.ui.card.widget

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.vb.payments.data.database.card.Card

class CardAdapter : ListAdapter<Card, CardViewHolder>(
    object : DiffUtil.ItemCallback<Card>() {
        override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem.cvv == newItem.cvv
                    && oldItem.number == newItem.number
                    && oldItem.expirationDate == newItem.expirationDate
        }
    }
) {
    var onCardClickListener: ((Card) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder = CardViewHolder(CardItemView(parent.context))

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        with(holder.itemView as CardItemView) {
            setItem(getItem(position))
            setOnClickListener {
                onCardClickListener?.invoke(getItem(position))
                isSelected = !isSelected
            }
        }
    }
}
