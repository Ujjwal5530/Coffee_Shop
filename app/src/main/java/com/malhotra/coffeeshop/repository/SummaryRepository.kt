package com.malhotra.coffeeshop.repository

import androidx.lifecycle.LiveData
import com.malhotra.coffeeshop.dao.SummaryDao
import com.malhotra.coffeeshop.modelclass.SummaryList

class SummaryRepository(private val dao : SummaryDao) {

    fun getOrder(orderNo : Int) : LiveData<List<SummaryList>> {
        return dao.getOrder(orderNo)
    }

    suspend fun insertOrder(list : SummaryList) {
        return dao.insertOrder(list)
    }

}