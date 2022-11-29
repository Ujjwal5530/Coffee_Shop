package com.malhotra.coffeeshop.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.malhotra.coffeeshop.modelclass.CartList

@Dao
interface CartOrderDao {

    @Query("SELECT * FROM CartList")
    fun getList() : LiveData<List<CartList>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(list : CartList)

    @Query("DELETE FROM CartList")
    suspend fun deleteList()

    @Delete
    suspend fun deleteItem(list: CartList)

    @Query("SELECT SUM(quantity) FROM CartList")
    fun totalItems() : Int

    @Query("SELECT SUM(price * quantity) FROM CartList")
    fun totalPrice() : Double

    @Query("UPDATE CartList SET quantity = :quantity WHERE id = :id")
    suspend fun updateQuantity(quantity : Int, id : Int)
}