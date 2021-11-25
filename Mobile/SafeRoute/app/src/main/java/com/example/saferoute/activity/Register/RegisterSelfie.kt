package com.example.saferoute.activity.Register

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.saferoute.R
import com.example.saferoute.activity.Login
import com.example.saferoute.activity.ViewPhoto
import com.example.saferoute.configuration.SafeRouteConstants
import com.example.saferoute.domain.Cadastro
import com.example.saferoute.endpoint.CadastroEndpoints
import com.example.saferoute.util.NetworkUtils
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException


class RegisterSelfie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_selfie)
    }

    fun goToSelfieScreen(v: View) {
        val selfieIntent: Intent = Intent(this, ViewPhoto::class.java)
        selfieIntent.putExtra("photoType", "register_selfie")
        startActivity(selfieIntent)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun collectSelfieData(v:View) {
        if(this.loadImageFromStorage("imageDir", "photo_register_selfie") != null){
            createObject()
        }
    }

    fun createObject(){
        val registerObject = intent.getSerializableExtra("registerObject") as Cadastro
        sendData(registerObject)
    }

    fun sendData(dadosCadastro: Cadastro){

        val selfie: MultipartBody.Part = MultipartBody.Part.createFormData("selfie", "selfie.jpeg", RequestBody.create(
            MediaType.parse("image/*"), this.loadImageFromStorage("imageDir", "photo_register_selfie")!!
        ))
        val fotoFrente: MultipartBody.Part = MultipartBody.Part.createFormData("fotoFrente", "fotoFrente.jpeg", RequestBody.create(
            MediaType.parse("image/*"), this.loadImageFromStorage("imageDir", "photo_register_document_front")!!
        ))
        val fotoVerso: MultipartBody.Part = MultipartBody.Part.createFormData("fotoVerso", "fotoVerso.jpeg", RequestBody.create(
            MediaType.parse("image/*"), this.loadImageFromStorage("imageDir", "photo_register_document_verse")!!
        ))

        val cadastroJson = Gson().toJson(dadosCadastro)
        val cadastro: MultipartBody.Part = MultipartBody.Part.createFormData("cadastro", cadastroJson)

        val loginScreen = Intent(this, Login::class.java);

        val retrofit = NetworkUtils.buildService(CadastroEndpoints::class.java, SafeRouteConstants.SAFE_ROUTE_API_BASE_URL)
        retrofit.cadastro(cadastro, selfie, fotoFrente, fotoVerso).enqueue(
            object : Callback<ResponseBody> {
                override fun onFailure(call:  Call<ResponseBody>, t: Throwable) {
                    throw t
                }
                override fun onResponse(call:  Call<ResponseBody>, response: Response<ResponseBody>) {
                    if(response.isSuccessful) {
                        notifyRegister()
                        startActivity(loginScreen)
                    }
                }
            }
        )
    }

    fun loadImageFromStorage(path: String, name: String): ByteArray? {
        try {
            val cw = ContextWrapper(applicationContext)
            val directory = cw.getDir(path, Context.MODE_PRIVATE)
            val f = File(directory, "$name.jpg")
            return FileInputStream(f).readBytes()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            return null
        }
    }

    fun notifyRegister() {
        Toast.makeText(this, "Cadastrada com sucesso!", Toast.LENGTH_LONG).show()
    }
}