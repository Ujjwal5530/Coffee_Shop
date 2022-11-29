package com.malhotra.coffeeshop.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.malhotra.coffeeshop.modelclass.ProfileInfo

@Dao
interface ProfileDao {

    @Query("SELECT * FROM Profile WHERE id = :id")
    fun getInfo(id : Int) : LiveData<ProfileInfo>

    @Query("SELECT * FROM Profile")
    fun getData() : LiveData<List<ProfileInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInfo(list :  ProfileInfo)

    @Update
    suspend fun updateInfo(list: ProfileInfo)
}