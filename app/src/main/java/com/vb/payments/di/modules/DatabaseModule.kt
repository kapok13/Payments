package com.vb.payments.di.modules

import androidx.room.Room
import com.vb.payments.data.database.CardDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val DATABASE_NAME = "card_db"

val databaseModule = module {
    single<CardDatabase> {
        Room.databaseBuilder(
            androidContext(),
            CardDatabase::class.java, DATABASE_NAME
        ).build()
    }
}
