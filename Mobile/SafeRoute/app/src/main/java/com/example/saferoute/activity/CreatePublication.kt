package com.example.saferoute.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.saferoute.R

class CreatePublication : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_publication)
    }

    fun openHome(v: View){
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
    }
}