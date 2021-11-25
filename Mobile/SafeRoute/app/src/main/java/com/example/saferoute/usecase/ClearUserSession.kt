package com.example.saferoute.usecase

import android.content.Context
import com.example.saferoute.database.AppLocalDatabase
import com.example.saferoute.domain.User

class ClearUserSession private constructor() {
    companion object {
        fun execute(context: Context) {
            val database: AppLocalDatabase = AppLocalDatabase.getInstance(context)
            val user: User? = database.UserDao().findRegistredUser()
            if(user != null && !(user.saveSession)) {
                database.UserDao().deleteAll()
            }
        }
    }
}