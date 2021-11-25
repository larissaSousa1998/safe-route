package com.example.saferoute.activity.Register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.saferoute.R
import com.example.saferoute.domain.Cadastro
import com.example.saferoute.domain.ContatoConfianca

class RegisterTrustContact : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_trust_contact)
    }

    fun collectTrustContactData(v: View){
        val edtTrustContactName: TextView = findViewById(R.id.edt_register_trust_contact_name)
        val edtTrustContactEmail: TextView = findViewById(R.id.edt_register_trust_contact_email)
        val edtTrustContactCelphone: TextView = findViewById(R.id.edt_register_trust_contact_celphone)

        val txtTrustContactName = edtTrustContactName.text.toString()
        val txtTrustContactEmail = edtTrustContactEmail.text.toString()
        val txtTrustContactCelphone = edtTrustContactCelphone.text.toString()

        val contatoConfianca = ContatoConfianca(null, txtTrustContactName, txtTrustContactEmail, txtTrustContactCelphone)

        createObject(contatoConfianca)
    }

    fun createObject(contatoConfianca : ContatoConfianca){
        val registerObject = intent.getSerializableExtra("registerObject") as Cadastro
        registerObject.contatoConfianca = contatoConfianca
//        registerObject.usuarioComum?.contatosConfianca = listOf(contatoConfianca)
        sendData(registerObject)
    }

    fun sendData(cadastro: Cadastro){
        val nextStep = Intent(this, RegisterDocument::class.java)
        nextStep.putExtra("registerObject", cadastro)
        startActivity(nextStep)
    }
}