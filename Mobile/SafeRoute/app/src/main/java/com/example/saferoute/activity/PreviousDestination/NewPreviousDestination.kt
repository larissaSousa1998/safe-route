package com.example.saferoute

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.saferoute.configuration.SafeRouteConstants
import com.example.saferoute.database.AppLocalDatabase
import com.example.saferoute.domain.DestinoPrevio
import com.example.saferoute.domain.Endereco
import com.example.saferoute.domain.User
import com.example.saferoute.endpoint.DestinoPrevioEndpoints
import com.example.saferoute.usecase.ClearUserSession
import com.example.saferoute.util.NetworkUtils
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class activity_new_previous_destination : AppCompatActivity() {

    var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_previous_destination)

        this.retrieveCurrentUser()
    }

    override fun onStop() {
        super.onStop()
        ClearUserSession.execute(applicationContext)
    }

    fun createNewPreviousDestination(view : View){
        val destinoPrevio: DestinoPrevio = getData()
        sendRequest(destinoPrevio)
    }

    fun sendRequest(destinoPrevio: DestinoPrevio){
        val retrofit = NetworkUtils.buildService(
            DestinoPrevioEndpoints::class.java,
            SafeRouteConstants.SAFE_ROUTE_API_BASE_URL)

        retrofit.createPreviousDestination(destinoPrevio).enqueue(
            object : Callback<Void> {
                override fun onFailure(call: Call<Void>, t: Throwable){
                    throw t
                }

                override fun onResponse(
                    call: Call<Void>,
                    response: Response<Void>
                ) {
                    Toast.makeText(applicationContext, "Destino prévio cadastrado!", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

    fun retrieveCurrentUser(): User? {
        val database: AppLocalDatabase = AppLocalDatabase.getInstance(applicationContext)
        println(database.UserDao().findRegistredUser())
        return database.UserDao().findRegistredUser()
    }

    fun getData(): DestinoPrevio{

        val title: TextInputEditText = findViewById(R.id.edt_previous_destination_info_name)
        val cep: TextInputEditText = findViewById(R.id.edt_previous_destination_cep)
        val logradouro: TextInputEditText = findViewById(R.id.edt_previous_destination_new_destination)
        val numero: TextInputEditText = findViewById(R.id.edt_previous_destination_number)
        val estado: TextInputEditText = findViewById(R.id.edt_previous_destination_state)

        return DestinoPrevio(title.text.toString(), null, logradouro.text.toString(), numero.text.toString(), cep.text.toString(), estado.text.toString(), user!!.id!!)
    }
}