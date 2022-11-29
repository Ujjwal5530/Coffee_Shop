package com.malhotra.coffeeshop.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.malhotra.coffeeshop.modelclass.CartList
import com.malhotra.coffeeshop.modelclass.SummaryList

@Dao
interface SummaryDao {

    @Query("SELECT * FROM OrderSummary WHERE orderNo = :orderNo")
    fun getOrder(orderNo : Int) : LiveData<List<SummaryList>>

    @Insert
    suspend fun insertOrder(list: SummaryList)


}