package com.vb.payments.data.model.card

import com.vb.payments.data.database.card.Card
import kotlinx.coroutines.flow.Flow

interface CardModel {
    val cards: Flow<List<Card>>
    fun insertCard(card: Card)
}
