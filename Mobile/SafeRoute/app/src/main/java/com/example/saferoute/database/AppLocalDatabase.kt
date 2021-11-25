package com.example.saferoute.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.saferoute.dao.UserDao
import com.example.saferoute.domain.User

@Database(entities = arrayOf(User::class), version = 1)
abstract class AppLocalDatabase: RoomDatabase() {
    abstract fun UserDao(): UserDao
    companion object {
        private var instance: AppLocalDatabase? = null

        fun getInstance(context: Context): AppLocalDatabase {
            if(instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    AppLocalDatabase::class.java, "safe-route"
                ).allowMainThreadQueries().build()
            }
            return instance!!
        }
    }
}