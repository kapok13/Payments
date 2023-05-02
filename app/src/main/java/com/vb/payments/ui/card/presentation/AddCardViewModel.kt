package com.vb.payments.ui.card.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vb.payments.data.database.card.Card
import com.vb.payments.data.model.card.CardModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddCardViewModel(
    private val cardModel: CardModel
) : ViewModel() {
    fun addCard(card: Card) { viewModelScope.launch(Dispatchers.Default) { cardModel.insertCard(card) } }
}
