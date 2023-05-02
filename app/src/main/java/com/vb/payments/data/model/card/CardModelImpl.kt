package com.vb.payments.data.model.card

import com.vb.payments.data.database.CardDatabase
import com.vb.payments.data.database.card.Card
import kotlinx.coroutines.flow.Flow

class CardModelImpl(
    private val cardDatabase: CardDatabase
) : CardModel {
    override val cards: Flow<List<Card>>
        get() = cardDatabase.cardDao().getCards()

    override fun insertCard(card: Card) {
        cardDatabase.cardDao().insert(card)
    }
}
