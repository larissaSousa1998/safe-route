package com.example.saferoute.activity.PreviousDestination

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.saferoute.R
import com.example.saferoute.domain.DestinoPrevio
import com.example.saferoute.usecase.ClearUserSession

class RoutePreviousDestination : AppCompatActivity() {

    lateinit var destinoPrevio: DestinoPrevio

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route_previous_destination)

        destinoPrevio = intent.getSerializableExtra("destinoPrevio") as DestinoPrevio

        val title : TextView = findViewById(R.id.txv_title_previous_destinations_map)
        val origem : TextView = findViewById(R.id.txv_origim_previous_destinations_map)
        val destino : TextView = findViewById(R.id.txv_destiny_previous_destionations_map)

        title.text = destinoPrevio.descricao
        origem.text = destinoPrevio.origem
        destino.text = destinoPrevio.logradouro

    }

    override fun onStop() {
        super.onStop()
        ClearUserSession.execute(applicationContext)
    }
}