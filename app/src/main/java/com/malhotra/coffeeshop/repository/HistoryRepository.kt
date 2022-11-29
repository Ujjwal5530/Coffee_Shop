package com.malhotra.coffeeshop.repository

import androidx.lifecycle.LiveData
import com.malhotra.coffeeshop.dao.HistoryDao
import com.malhotra.coffeeshop.modelclass.HistoryList

class HistoryRepository(private val dao: HistoryDao) {

    suspend fun insertOrder(list: HistoryList){
        dao.insertOrder(list)
    }

    fun getOrderList() : LiveData<List<HistoryList>>{
        return dao.getOrderList()
    }
}