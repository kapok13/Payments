package com.vb.payments.data.database.card

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {
    @Query("SELECT * FROM card")
    fun getCards(): Flow<List<Card>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(card: Card)
}
