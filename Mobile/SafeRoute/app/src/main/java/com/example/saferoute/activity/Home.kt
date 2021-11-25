package com.example.saferoute.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.saferoute.R
import com.example.saferoute.activity.GroupLocomotion.UserGroupsLocomotion
import com.example.saferoute.activity.PreviousDestination.PreviousDestination
import com.example.saferoute.adapter.PublicacaoFeedAdapter
import com.example.saferoute.configuration.SafeRouteConstants
import com.example.saferoute.database.AppLocalDatabase
import com.example.saferoute.domain.GroupoLocomocaoUsuaria
import com.example.saferoute.domain.Publicacao
import com.example.saferoute.domain.PublicacaoFeed
import com.example.saferoute.domain.User
import com.example.saferoute.endpoint.GruposLocomocaoEndpoints
import com.example.saferoute.endpoint.PublicacaoEndpoints
import com.example.saferoute.response.PublicacaoResponse
import com.example.saferoute.usecase.ClearUserSession
import com.example.saferoute.util.NetworkUtils
import com.google.android.gms.location.FusedLocationProviderClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class Home : AppCompatActivity() {

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        printUserInfo()
        getPublications()
    }

    fun printUserInfo() {
        val userData = intent.getSerializableExtra("userData") as User
        val name = userData.name

        val tvHomeTitle: TextView = findViewById(R.id.tv_home_title)
        tvHomeTitle.text = getString(R.string.home_welcome, this.getGreetingByTime(), name)
    }

    fun getGreetingByTime(): String {
        val calendar: Calendar = Calendar.getInstance(TimeZone.getDefault())

        return when(calendar.get(Calendar.HOUR_OF_DAY)) {
            in 4 .. 11 -> getString(R.string.good_morning)
            in 12 .. 18 -> getString(R.string.good_afternoon)
            else -> getString(R.string.good_evening)
        }
    }

    fun getPublications() {
        val retrofit = NetworkUtils.buildService(
            PublicacaoEndpoints::class.java,
            SafeRouteConstants.SAFE_ROUTE_API_BASE_URL)
        retrofit.getPublicacoes().enqueue(
            object : Callback<PublicacaoResponse> {
                override fun onFailure(call: Call<PublicacaoResponse>, t: Throwable) {
                    throw t
                }
                override fun onResponse(call: Call<PublicacaoResponse>, response: Response<PublicacaoResponse>) {
                    val publicacoes: PublicacaoResponse? = response.body()
                    listPublicacoes(publicacoes!!.publicacaoes)
                }
            }
        )
    }

    fun listPublicacoes(publicacoes: List<Publicacao>) {
        val recycler: RecyclerView = findViewById(R.id.recycler_view_feed)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = PublicacaoFeedAdapter(publicacoes)
    }

    fun openGroupLocomotiom(v: View){
        val intent = Intent(this, UserGroupsLocomotion::class.java)
        startActivity(intent)
    }

    fun openPreviousDestination(v: View){
        val intent = Intent(this, PreviousDestination::class.java)
        startActivity(intent)
    }

    fun openComplaint(v: View){
        val intent = Intent(this, RegisterComplaint::class.java)
        startActivity(intent)
    }


    fun goToRouteCurrent(v: View){
        val goRoute = Intent(this, RouteCurrentMoment::class.java)
        startActivity(goRoute)
    }

    fun openCreatePublication(v: View){
        val intent = Intent(this, CreatePublication::class.java)
        startActivity(intent)
    }

    override fun onStop() {
        super.onStop()
        ClearUserSession.execute(applicationContext)
    }

}