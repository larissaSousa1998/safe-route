package com.example.saferoute.activity.Register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.saferoute.R
import com.example.saferoute.configuration.SafeRouteConstants
import com.example.saferoute.domain.*
import com.example.saferoute.endpoint.ViaCepEndpoints
import com.example.saferoute.response.ViaCepResponse
import com.example.saferoute.util.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterPreviousDestination : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_previous_destination)
    }

    fun collectPreviousDestinationData(v: View){
        val edtPreviousDestinationDescription: TextView = findViewById(R.id.edt_register_destination_description)
        val edtPreviousDestinationCep: TextView = findViewById(R.id.edt_register_destination_cep)
        val edtPreviousDestinationPublicPlace: TextView = findViewById(R.id.edt_register_destination_public_place)
        val edtPreviousDestinationNumber: TextView = findViewById(R.id.edt_register_destination_number)

        val previousDestinationDescription = edtPreviousDestinationDescription.text.toString()
        val previousDestinationCep = edtPreviousDestinationCep.text.toString()
        val previousDestinationPublicPlace = edtPreviousDestinationPublicPlace.text.toString()
        val previousDestinationNumber = edtPreviousDestinationNumber.text.toString()

        val endereco = Endereco(null, previousDestinationDescription, previousDestinationCep, previousDestinationPublicPlace, previousDestinationNumber, null)

        this.searchForUfByCep(previousDestinationCep, endereco)
    }

    fun searchForUfByCep(cep: String, endereco: Endereco) {
        val retrofit = NetworkUtils.buildService(ViaCepEndpoints::class.java, SafeRouteConstants.VIA_CEP_API_BASE_URL)
        retrofit.search(cep).enqueue(
            object : Callback<ViaCepResponse> {
                override fun onFailure(call: Call<ViaCepResponse>, t: Throwable) {
                    throw t
                }
                override fun onResponse(call: Call<ViaCepResponse>, response: Response<ViaCepResponse>) {
                    endereco.state = response.body()?.uf
                    createObject(endereco)
                }
            }
        )
    }

    fun createObject(endereco: Endereco){
        val registerObject = intent.getSerializableExtra("registerObject") as Cadastro
        registerObject.endereco = endereco
//        registerObject.usuarioComum?.enderecos = listOf(endereco)
        sendData(registerObject)
    }

    fun sendData(cadastro: Cadastro){
        val nextStep = Intent(this, RegisterTrustContact::class.java)
        nextStep.putExtra("registerObject", cadastro)
        startActivity(nextStep)
    }
}