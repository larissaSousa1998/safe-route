package com.example.saferoute.activity.GroupLocomotion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.saferoute.R
import com.example.saferoute.usecase.ClearUserSession

class RegisterGroupLocomotion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_group_locomotion)
    }

    override fun onStop() {
        super.onStop()
        ClearUserSession.execute(applicationContext)
    }
}