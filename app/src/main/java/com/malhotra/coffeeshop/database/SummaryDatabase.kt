package com.malhotra.coffeeshop.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.malhotra.coffeeshop.dao.SummaryDao
import com.malhotra.coffeeshop.modelclass.SummaryList

@Database(entities = [SummaryList::class], version = 1, exportSchema = false)
abstract class SummaryDatabase : RoomDatabase(){

    abstract fun dao() : SummaryDao

    companion object{

        @Volatile
        private var INSTANCE : SummaryDatabase? = null

        fun getInstance(context: Context) : SummaryDatabase {

            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }

            synchronized(this){
                val database = Room.databaseBuilder(context, SummaryDatabase::class.java, "Summary")
                    .build()
                INSTANCE  = database
                return database
            }
        }

    }
}