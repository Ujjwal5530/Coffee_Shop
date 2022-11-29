package com.malhotra.coffeeshop.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.malhotra.coffeeshop.dao.FavDao
import com.malhotra.coffeeshop.modelclass.FavList
import java.time.Instant

@Database(entities = [FavList::class], version = 1, exportSchema = true)
abstract class FavDatabase : RoomDatabase() {
    abstract fun dao() : FavDao

    companion object{
        @Volatile
        private var INSTANCE : FavDatabase? = null

        fun getInstance(context: Context) : FavDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val database = Room
                    .databaseBuilder(context, FavDatabase::class.java, "Favourites")
                    .build()
                INSTANCE = database
                return database
            }
        }


    }
}