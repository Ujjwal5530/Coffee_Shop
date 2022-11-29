package com.malhotra.coffeeshop.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.malhotra.coffeeshop.dao.HistoryDao
import com.malhotra.coffeeshop.modelclass.HistoryList

@Database(entities = [HistoryList::class], version = 1, exportSchema = false)
abstract class HistoryDatabase : RoomDatabase() {
    abstract fun dao() : HistoryDao

    companion object{

        @Volatile
        private var INSTANCE : HistoryDatabase? = null

        fun getInstance(context: Context) : HistoryDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this){
                val database = Room.databaseBuilder(context, HistoryDatabase::class.java, "Order History")
                    .build()
                INSTANCE = database
                return database
            }
        }
    }
}