package com.malhotra.coffeeshop.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.malhotra.coffeeshop.modelclass.HistoryList

@Dao
interface HistoryDao {
    @Query("SELECT * FROM OrderHistory")
    fun getOrderList() : LiveData<List<HistoryList>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(list: HistoryList)
}