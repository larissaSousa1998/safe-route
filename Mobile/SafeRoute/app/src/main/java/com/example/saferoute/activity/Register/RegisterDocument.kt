package com.example.saferoute.activity.Register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.saferoute.R
import com.example.saferoute.activity.ViewPhoto
import com.example.saferoute.domain.Cadastro
import com.example.saferoute.domain.Documento

class RegisterDocument : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_document)
    }

    fun collectDocumentData(v: View) {
        val edtRegisterDocumentType: TextView = findViewById(R.id.edt_register_document_type)
        val registerDocumentType = edtRegisterDocumentType.text.toString()

        val edtRegisterDocumentNumber: TextView = findViewById(R.id.edt_register_document_number)
        val registerDocumentNumber = edtRegisterDocumentNumber.text.toString()

        val documento = Documento(null, registerDocumentType, null, null, registerDocumentNumber)

        createObject(documento)
    }

    fun createObject(documento: Documento){
        val registerObject = intent.getSerializableExtra("registerObject") as Cadastro
        registerObject.documento = documento
        sendData(registerObject)
    }

    fun sendData(cadastro: Cadastro){
        val nextStep = Intent(this, RegisterSelfie::class.java)
        nextStep.putExtra("registerObject", cadastro)
        startActivity(nextStep)
    }

    fun takeDocumentFrontPhoto(v: View) {
        val photoIntent: Intent = Intent(this, ViewPhoto::class.java)
        photoIntent.putExtra("photoType", "register_document_front")
        startActivity(photoIntent)
    }

    fun takeDocumentVersePhoto(v: View) {
        val photoIntent: Intent = Intent(this, ViewPhoto::class.java)
        photoIntent.putExtra("photoType", "register_document_verse")
        startActivity(photoIntent)
    }
}