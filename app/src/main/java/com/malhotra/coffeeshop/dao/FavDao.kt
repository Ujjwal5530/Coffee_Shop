package com.malhotra.coffeeshop.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.malhotra.coffeeshop.modelclass.FavList

@Dao
interface FavDao {

    @Query("SELECT * FROM Favourites")
    fun getFavList() : LiveData<List<FavList>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFav(list: FavList)

    @Update
    suspend fun updateFav(list: FavList)

    @Delete
    suspend fun deleteFav(list: FavList)
}