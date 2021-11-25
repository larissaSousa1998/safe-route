package com.example.saferoute.activity.GroupLocomotion

import android.content.Intent
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.saferoute.R
import com.example.saferoute.activity.Home
import com.example.saferoute.activity.PreviousDestination.PreviousDestination
import com.example.saferoute.activity.RegisterComplaint
import com.example.saferoute.adapter.GruposLocomocaoAdapter
import com.example.saferoute.configuration.SafeRouteConstants
import com.example.saferoute.database.AppLocalDatabase
import com.example.saferoute.domain.*
import com.example.saferoute.endpoint.GruposLocomocaoEndpoints
import com.example.saferoute.usecase.ClearUserSession
import com.example.saferoute.util.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class UserGroupsLocomotion : AppCompatActivity(),  GruposLocomocaoAdapter.OnItemClickListener{
    var viagem = Viagem()
    lateinit var grupoViagem: GroupoLocomocaoUsuaria
    lateinit var geocoder: Geocoder

    lateinit var grupos: List<GroupoLocomocaoUsuaria>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_groups_locomotion)

        geocoder = Geocoder(this, Locale.getDefault())

        var user: User = this.retrieveCurrentUser()!!
        this.retrieveUserLocomotionGroups(user)
    }

    override fun onItemClick(position: Int) {
        grupoViagem = grupos[position]

        viagem.nomeOrigem = grupos[position].viagem.nomeOrigem
        viagem.latitudeOrigem = grupos[position].viagem.latitudeOrigem
        viagem.longitudeOrigem = grupos[position].viagem.longitudeOrigem

        viagem.nomeDestino = grupos[position].viagem.nomeDestino
        viagem.latitudeDestino = grupos[position].viagem.latitudeDestino
        viagem.longitudeDestino = grupos[position].viagem.longitudeDestino

        goToMaps()
    }

    // MAPS

//    fun getLatLng(endereco: String) {
//        val urlLatLng =
//            "https://maps.googleapis.com/maps/api/geocode/json?address=${endereco}&key=${BuildConfig.GOOGLE_MAPS_API_KEY        }"
//        val latlgnRequest = object : StringRequest(Request.Method.GET, urlLatLng, Response.Listener<String> {
//                response ->
//            val jsonResponse = JSONObject(response)
//            val results = jsonResponse.getJSONArray("results")
//            val geometry = results.getJSONObject(0).getJSONObject("geometry")
//            val location = geometry.getJSONObject("location")
//            createObject(endereco, location.getString("lat"), location.getString("lng"))
//        }, Response.ErrorListener {
//                response ->
//            println(response)
//        }){}
//        val requestQueue = Volley.newRequestQueue(this)
//        requestQueue.add(latlgnRequest)
//    }

//    fun createObject(endereco: String, latitude: String, longitude: String) {
//        if(viagem.origem == null){
//            viagem.origem = Localizacao(endereco, latitude, longitude)
//        } else {
//            viagem.destino = Localizacao(endereco, latitude, longitude)
//            if(viagem.origem != null && viagem.destino != null){
//                goToMaps()
//            }
//        }
//    }

    fun goToMaps(){
        val goMaps = Intent(this, RouteGroupLocomotion::class.java)
        goMaps.putExtra("viagem", viagem)
        goMaps.putExtra("grupoViagem", grupoViagem)
        startActivity(goMaps)
    }

    fun openHome(v: View){
        val intent = Intent(this, Home::class.java)
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

    fun retrieveCurrentUser(): User? {
        val database: AppLocalDatabase = AppLocalDatabase.getInstance(applicationContext)
        return database.UserDao().findRegistredUser()
    }

    fun retrieveUserLocomotionGroups(user: User) {
        val retrofit = NetworkUtils.buildService(
            GruposLocomocaoEndpoints::class.java,
            SafeRouteConstants.SAFE_ROUTE_API_BASE_URL)

        retrofit.retrieveGroupsByUser(user.id!!).enqueue(
            object : Callback<List<GroupoLocomocaoUsuaria>> {
                override fun onFailure(call: Call<List<GroupoLocomocaoUsuaria>>, t: Throwable) {
                    throw t
                }
                override fun onResponse(call: Call<List<GroupoLocomocaoUsuaria>>, response: Response<List<GroupoLocomocaoUsuaria>>) {
                    val groups: List<GroupoLocomocaoUsuaria>? = response.body()
                    groups!!.forEach{group -> fillViagemNames(group)}
                    grupos = groups
                    listGroups(groups)
                }
            }
        )
    }

    fun fillViagemNames(group: GroupoLocomocaoUsuaria) {
        val viagem: Viagem = group.viagem
        viagem.nomeOrigem = this.getAddress(viagem.latitudeOrigem!!.toDouble(), viagem.longitudeOrigem!!.toDouble())
        viagem.nomeDestino = this.getAddress(viagem.latitudeDestino!!.toDouble(), viagem.longitudeDestino!!.toDouble())
    }

    fun getAddress(latitude: Double, longitude: Double): String {
        val addresses: List<Address> = geocoder.getFromLocation(latitude, longitude, 1)
        return this.wrapStreetName(addresses.get(0).getAddressLine(0))
    }

    fun listGroups(groups: List<GroupoLocomocaoUsuaria>) {
        val v: RecyclerView = findViewById(R.id.recycler_view_groups_locomotion)
        v.layoutManager = LinearLayoutManager(this)
        v.adapter = GruposLocomocaoAdapter(grupos = groups, this)
    }

    fun wrapStreetName(completeAddress: String): String {
        val endIndex: Int = completeAddress.indexOf("-", completeAddress.indexOf("-") + 1)
        return completeAddress.substring(0, endIndex)
    }

    override fun onStop() {
        super.onStop()
        ClearUserSession.execute(applicationContext)
    }
}