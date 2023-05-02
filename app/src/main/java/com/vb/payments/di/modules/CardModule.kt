package com.vb.payments.di.modules

import com.vb.payments.data.database.CardDatabase
import com.vb.payments.data.model.card.CardModel
import com.vb.payments.data.model.card.CardModelImpl
import com.vb.payments.ui.card.presentation.AddCardViewModel
import com.vb.payments.ui.card.presentation.CardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module

val cardModule = module {
    single<CardModel> {
        CardModelImpl(get(CardDatabase::class.java))
    }
    viewModel<AddCardViewModel> {
        AddCardViewModel(get(CardModel::class.java))
    }
    viewModel<CardViewModel> {
        CardViewModel(get(CardModel::class.java))
    }
}
