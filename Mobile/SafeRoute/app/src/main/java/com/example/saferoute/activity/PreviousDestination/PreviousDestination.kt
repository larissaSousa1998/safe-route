package com.example.saferoute.activity.PreviousDestination

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.saferoute.R
import com.example.saferoute.activity.GroupLocomotion.UserGroupsLocomotion
import com.example.saferoute.activity.Home
import com.example.saferoute.activity.RegisterComplaint
import com.example.saferoute.activity_new_previous_destination
import com.example.saferoute.adapter.DestinoPrevioAdapter
import com.example.saferoute.configuration.SafeRouteConstants
import com.example.saferoute.database.AppLocalDatabase
import com.example.saferoute.domain.DestinoPrevio
import com.example.saferoute.domain.User
import com.example.saferoute.endpoint.DestinoPrevioEndpoints
import com.example.saferoute.endpoint.GruposLocomocaoEndpoints
import com.example.saferoute.usecase.ClearUserSession
import com.example.saferoute.util.NetworkUtils
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PreviousDestination : AppCompatActivity(), DestinoPrevioAdapter.OnItemClickListener {

    lateinit var destinos: List<DestinoPrevio>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_previous_destinations)

        var user: User = this.retrieveCurrentUser()!!
        this.retrieveUserPreviousDestinations(user)
    }

    override fun onItemClick(position: Int) {
        val destinoPrevio = Intent(this, RoutePreviousDestination::class.java)
        destinoPrevio.putExtra("destinoPrevio", destinos[position])
        startActivity(destinoPrevio)

    }

    fun btnNewDestination(v: View){
        val intent = Intent(this, activity_new_previous_destination::class.java)
        startActivity(intent)
    }

    fun openHome(v: View){
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
    }

    fun openGroupLocomotiom(v: View){
        val intent = Intent(this, UserGroupsLocomotion::class.java)
        startActivity(intent)
    }

    fun openComplaint(v: View){
        val intent = Intent(this, RegisterComplaint::class.java)
        startActivity(intent)
    }

    fun retrieveCurrentUser(): User? {
        val database: AppLocalDatabase = AppLocalDatabase.getInstance(applicationContext)
        return database.UserDao().findRegistredUser()
    }

    fun retrieveUserPreviousDestinations(user: User){
        val retrofit = NetworkUtils.buildService(
            DestinoPrevioEndpoints::class.java,
            SafeRouteConstants.SAFE_ROUTE_API_BASE_URL)

        retrofit.retrievePreviousDestinationsbyUser(user.id!!).enqueue(
            object : Callback<List<DestinoPrevio>> {
                override fun onFailure(call: Call<List<DestinoPrevio>>, t: Throwable){
                    throw t
                }

                override fun onResponse(
                    call: Call<List<DestinoPrevio>>,
                    response: Response<List<DestinoPrevio>>
                ) {
                    val destinations: List<DestinoPrevio>? = response.body()
                    destinos = destinations!!
                    listDestinations(destinations)
                }
            }
        )
    }

    fun listDestinations(destinations: List<DestinoPrevio>){

        val v: RecyclerView = findViewById(R.id.recycler_view_previous_destinations)
        v.layoutManager = LinearLayoutManager(this)
        v.adapter = DestinoPrevioAdapter(destinos = destinations, this)
    }

    override fun onStop() {
        super.onStop()
        ClearUserSession.execute(applicationContext)
    }

}