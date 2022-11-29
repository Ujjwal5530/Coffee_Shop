package com.malhotra.coffeeshop.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.malhotra.coffeeshop.dao.ProfileDao
import com.malhotra.coffeeshop.modelclass.ProfileInfo

@Database(entities = [ProfileInfo::class], version = 1, exportSchema = false)
abstract class ProfileDatabase : RoomDatabase(){
    abstract fun dao() : ProfileDao

    companion object{
        @Volatile
        private var INSTANCE : ProfileDatabase? = null

        fun getInstance(context: Context) : ProfileDatabase{
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val database = Room.databaseBuilder(context, ProfileDatabase::class.java, "Profile")
                    .build()
                INSTANCE = database
                return database
            }
        }
    }
}