package com.example.saferoute.activity.Register

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.saferoute.R
import com.example.saferoute.domain.Cadastro
import com.example.saferoute.domain.UsuarioComum
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RegisterPersonalInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_personal_info)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun collectPersonalInfoData(v: View){
        val edtPersonalInfoName: TextView = findViewById(R.id.edt_register_personal_info_name)
        val edtPersonalInfoEmail: TextView = findViewById(R.id.edt_register_personal_info_email)
        val edtPersonalInfoPassword: TextView = findViewById(R.id.edt_register_personal_info_password)
        val edtPersonalInfoBirthdate: TextView = findViewById(R.id.edt_register_personal_info_birthdate)

        val personalInfoName = edtPersonalInfoName.text.toString()
        val personalInfoEmail = edtPersonalInfoEmail.text.toString()
        val personalInfoPassword = edtPersonalInfoPassword.text.toString()
        val personalInfoBirthdate = edtPersonalInfoBirthdate.text.toString()

//        val registerDate = LocalDateTime.now()

        val birthDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
//        val registerDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")

        val usuarioComum = UsuarioComum(null, personalInfoName, personalInfoEmail, personalInfoPassword,
            LocalDate.parse(personalInfoBirthdate, birthDateFormatter).toString())

        createObject(usuarioComum)
    }

    fun createObject(usuarioComum: UsuarioComum){
        val registerObject = Cadastro()
        registerObject.usuarioComum = usuarioComum
        sendData(registerObject)
    }

    fun sendData(cadastro: Cadastro){
        val nextStep = Intent(this, RegisterPreviousDestination::class.java)
        nextStep.putExtra("registerObject", cadastro)
        startActivity(nextStep)
    }
}

