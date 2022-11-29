package com.malhotra.coffeeshop.repository

import androidx.lifecycle.LiveData
import com.malhotra.coffeeshop.dao.CartOrderDao
import com.malhotra.coffeeshop.modelclass.CartList

class CartRepository(private val dao : CartOrderDao) {

    fun getList() : LiveData<List<CartList>>{
        return dao.getList()
    }

    suspend fun insertList(list: CartList){
        dao.insertList(list)
    }

    suspend fun deleteList(){
        dao.deleteList()
    }

    fun totalItems() :Int {
        return dao.totalItems()
    }

    suspend fun updateQuantity(quantity : Int, id : Int){
        dao.updateQuantity(quantity, id)
    }

    suspend fun deleteItem(list: CartList){
        dao.deleteItem(list)
    }

    fun totalPrice() : Double{
       return dao.totalPrice()
    }
}