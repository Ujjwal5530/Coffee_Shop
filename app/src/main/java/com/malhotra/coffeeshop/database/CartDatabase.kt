package com.malhotra.coffeeshop.database

import android.content.Context
import androidx.room.*
import com.malhotra.coffeeshop.dao.CartOrderDao
import com.malhotra.coffeeshop.modelclass.CartList

@Database(entities = [CartList::class], version = 1, exportSchema = false)
abstract class CartDatabase : RoomDatabase() {
    abstract fun dao() : CartOrderDao

    companion object{
        @Volatile
        private var INSTANCE : CartDatabase? = null
        fun getDatabaseInstance(context: Context) : CartDatabase{

            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val database = Room
                    .databaseBuilder(context, CartDatabase::class.java, "CartList")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = database
                return database
            }
        }
    }
}