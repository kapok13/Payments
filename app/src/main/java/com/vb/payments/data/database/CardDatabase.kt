package com.vb.payments.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vb.payments.data.database.card.CardDao
import com.vb.payments.data.database.card.Card

@Database(entities = [Card::class], version = 1)
abstract class CardDatabase : RoomDatabase() {
    abstract fun cardDao(): CardDao
}
