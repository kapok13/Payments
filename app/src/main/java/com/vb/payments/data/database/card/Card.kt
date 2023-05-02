package com.vb.payments.data.database.card

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card")
data class Card(
    val type: String,
    val number: String,
    val expirationDate: String,
    val cvv: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}
