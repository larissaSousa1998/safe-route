package com.example.saferoute.activity

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.saferoute.R
import com.example.saferoute.usecase.ClearUserSession
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.view.View
import android.widget.EditText
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.saferoute.BuildConfig
import com.example.saferoute.domain.Localizacao
import com.example.saferoute.domain.Viagem
import com.example.saferoute.usecase.ListDangerAreas
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.PolyUtil
import org.json.JSONObject


class RouteCurrentMoment : AppCompatActivity(), OnMapReadyCallback {
    private var googleMap: GoogleMap? = null
    var currentLocation : Location? = null
    var fusedLocationProviderClient: FusedLocationProviderClient? = null
    val REQUEST_CODE = 101
    var viagem = Viagem()
    lateinit var listDangerAreas: ListDangerAreas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route_current_moment)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        fetchLocation()

    }

    @SuppressLint("MissingPermission")
    private fun fetchLocation() {
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE)
            return
        }

        val task = fusedLocationProviderClient!!.lastLocation

        task.addOnSuccessListener { location ->
            if (location != null){
                currentLocation = location
                val supportMapFragment = (supportFragmentManager.findFragmentById(R.id.map_fragment_route_current) as SupportMapFragment?)
                supportMapFragment!!.getMapAsync(this@RouteCurrentMoment)
            }
        }
    }

    var map: GoogleMap? = null
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val latLng = LatLng(-23.5581164,-46.6638027)
//        viagem.latitudeOrigem = currentLocation!!.latitude.toString()
//        viagem.longitudeOrigem = currentLocation!!.longitude.toString
        viagem.latitudeOrigem = latLng.latitude.toString()
        viagem.longitudeOrigem = latLng.longitude.toString()
        viagem.nomeOrigem = "Você está aqui!"
//        listDangerAreas.execute(googleMap!!)
        val markerOptions = MarkerOptions().position(latLng).title(viagem.nomeOrigem)
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15f))
        googleMap.addMarker(markerOptions)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            REQUEST_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    fetchLocation()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onStop() {
        super.onStop()
        ClearUserSession.execute(applicationContext)
    }

    fun getDestinationText(v:View) {
        val edtDestino: EditText = findViewById(R.id.edt_search_destination)
        val edtDestinoText = edtDestino.text.toString()
        getLatLng(edtDestinoText)
    }
    fun getLatLng(endereco: String) {
        val urlLatLng =
            "https://maps.googleapis.com/maps/api/geocode/json?address=${endereco}&key=${BuildConfig.GOOGLE_MAPS_API_KEY}"
        val latlgnRequest = object : StringRequest(Request.Method.GET, urlLatLng, Response.Listener<String> {
                response ->
            val jsonResponse = JSONObject(response)
            val results = jsonResponse.getJSONArray("results")
            val geometry = results.getJSONObject(0).getJSONObject("geometry")
            val location = geometry.getJSONObject("location")
            createObject(endereco, location.getString("lat"), location.getString("lng"))
        }, Response.ErrorListener {
                response ->
            println(response)
        }){}
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(latlgnRequest)
    }

    fun createObject(endereco: String, latitude: String, longitude: String) {
            viagem.longitudeDestino = longitude
            viagem.latitudeDestino = latitude
            viagem.nomeDestino = endereco
            print(viagem)
            if(viagem!=null){
                createRoute(map!!)
            }
        }

    fun createRoute (googleMap: GoogleMap){
        this.googleMap = googleMap

        val latLngOrigin = LatLng(viagem.latitudeOrigem!!.toDouble(), viagem.longitudeOrigem!!.toDouble())
        val latLngDestination = LatLng(viagem.latitudeDestino!!.toDouble(), viagem.longitudeDestino!!.toDouble())



        this.googleMap!!.addMarker(
            MarkerOptions()
                .position(latLngOrigin)
                .title(viagem.nomeOrigem)
        )
        this.googleMap!!.addMarker(
            MarkerOptions()
                .position(latLngDestination)
                .title(viagem.nomeDestino)
        )
        this.googleMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(-23.5552714,-46.6642275), 14.5f))


        val path: MutableList<List<LatLng>> = ArrayList()
        val urlDirections = "https://maps.googleapis.com/maps/api/directions/json?origin=${viagem.latitudeOrigem},${viagem.longitudeOrigem}&destination=${viagem.latitudeDestino},${viagem.longitudeDestino}&key=${BuildConfig.GOOGLE_MAPS_API_KEY}"
        val directionsRequest = object : StringRequest(Request.Method.GET, urlDirections, Response.Listener<String> {
                response ->
            val jsonResponse = JSONObject(response)
            println(jsonResponse)
            // Get routes
            val routes = jsonResponse.getJSONArray("routes")
            val legs = routes.getJSONObject(0).getJSONArray("legs")
            val steps = legs.getJSONObject(0).getJSONArray("steps")
            for (i in 0 until steps.length()) {
                val points = steps.getJSONObject(i).getJSONObject("polyline").getString("points")
                path.add(PolyUtil.decode(points))
            }
            for (i in 0 until path.size) {
                this.googleMap!!.addPolyline(PolylineOptions().addAll(path[i]).color(Color.parseColor("#FFBA68C8")))
            }
        }, Response.ErrorListener {
                _ ->
        }){}
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(directionsRequest)
        }
    }

