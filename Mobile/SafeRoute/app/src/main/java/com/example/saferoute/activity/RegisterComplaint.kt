package com.example.saferoute.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.saferoute.R
import com.example.saferoute.activity.GroupLocomotion.UserGroupsLocomotion
import com.example.saferoute.activity.PreviousDestination.PreviousDestination
import com.example.saferoute.usecase.ClearUserSession

class RegisterComplaint : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_complaint)
    }

    fun openHome(v: View){
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
    }

    fun openGroupLocomotiom(v: View){
        val intent = Intent(this, UserGroupsLocomotion::class.java)
        startActivity(intent)
    }

    fun openPreviousDestination(v: View){
        val intent = Intent(this, PreviousDestination::class.java)
        startActivity(intent)
    }

    override fun onStop() {
        super.onStop()
        ClearUserSession.execute(applicationContext)
    }
}