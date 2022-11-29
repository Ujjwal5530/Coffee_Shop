package com.malhotra.coffeeshop.repository

import androidx.lifecycle.LiveData
import com.malhotra.coffeeshop.dao.FavDao
import com.malhotra.coffeeshop.modelclass.FavList

class FavRepository(private val dao: FavDao) {

    fun getFavList() : LiveData<List<FavList>> {
        return dao.getFavList()
    }

    suspend fun insertFav(list: FavList){
        dao.insertFav(list)
    }

    suspend fun deleteFav(list : FavList) {
        dao.deleteFav(list)
    }

    suspend fun updateList(list: FavList) {
        dao.updateFav(list)
    }
}