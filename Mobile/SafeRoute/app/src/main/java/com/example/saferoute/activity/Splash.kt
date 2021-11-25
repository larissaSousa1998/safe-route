package com.example.saferoute.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.saferoute.R
import com.example.saferoute.activity.GroupLocomotion.UserGroupsLocomotion
import com.example.saferoute.database.AppLocalDatabase
import com.example.saferoute.domain.User
import com.example.saferoute.usecase.ClearUserSession

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val user: User? = this.checkSession()
        if(user != null) {
            this.goHome(user)
        }
    }

    fun Tela_login(v: View) {
        startActivity(Intent(this, Login::class.java))
    }

    fun checkSession(): User? {
        val database: AppLocalDatabase = AppLocalDatabase.getInstance(applicationContext)
        return database.UserDao().findRegistredUser()
    }

    fun goHome(user: User){
        val home = Intent(this, Home::class.java)
        home.putExtra("userData", user)
        startActivity(home)
    }
}