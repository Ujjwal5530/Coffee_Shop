package com.malhotra.coffeeshop.repository

import androidx.lifecycle.LiveData
import com.malhotra.coffeeshop.dao.ProfileDao
import com.malhotra.coffeeshop.modelclass.ProfileInfo

class ProfileRepository(private val dao : ProfileDao) {

    fun getInfo(id : Int) : LiveData<ProfileInfo>{
        return dao.getInfo(id)
    }

    fun getData() : LiveData<List<ProfileInfo>> {
        return dao.getData()
    }

    suspend fun insertInfo(list:ProfileInfo){
        dao.insertInfo(list)
    }

    suspend fun updateInfo(list: ProfileInfo){
        dao.updateInfo(list)
    }
}