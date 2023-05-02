package com.vb.payments.ui.card.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vb.payments.data.database.card.Card
import com.vb.payments.data.model.card.CardModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.stateIn

class CardViewModel(
    private val cardModel: CardModel
) : ViewModel() {
    val selectedCard = MutableStateFlow<Card?>(null)
    suspend fun getCards() = cardModel.cards.stateIn(viewModelScope)
}
