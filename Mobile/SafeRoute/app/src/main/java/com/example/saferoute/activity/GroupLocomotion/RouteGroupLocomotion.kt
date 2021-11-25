package com.example.saferoute.activity.GroupLocomotion

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.maps.android.PolyUtil
import org.json.JSONObject

import com.example.saferoute.BuildConfig
import com.example.saferoute.R
import com.example.saferoute.usecase.ListDangerAreas
import com.example.saferoute.domain.GroupoLocomocaoUsuaria
import com.example.saferoute.domain.Viagem
import com.example.saferoute.usecase.ClearUserSession
import com.google.android.gms.maps.model.*


class RouteGroupLocomotion : AppCompatActivity(), OnMapReadyCallback {

    private var googleMap: GoogleMap? = null
    lateinit var viagem: Viagem
    lateinit var grupoViagem: GroupoLocomocaoUsuaria
    val listDangerAreas: ListDangerAreas = ListDangerAreas()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route_group_locomotion)

        viagem = intent.getSerializableExtra("viagem") as Viagem
        grupoViagem = intent.getSerializableExtra("grupoViagem") as GroupoLocomocaoUsuaria

        val txv_title: TextView = findViewById(R.id.txv_title_group_route)
        val txv_origim: TextView = findViewById(R.id.txv_origim_group_route)
        val txv_destino: TextView = findViewById(R.id.txv_destiny_group_route)
        val txv_adm: TextView = findViewById(R.id.txv_name_adm_group_route)

        txv_title.text = grupoViagem.title
        txv_origim.text = grupoViagem.viagem.nomeOrigem
        txv_destino.text = grupoViagem.viagem.nomeDestino
        txv_adm.text = grupoViagem.nome

        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        this.googleMap = googleMap

        val latLngOrigin = LatLng(viagem.latitudeOrigem!!.toDouble(), viagem.longitudeOrigem!!.toDouble())
        val latLngDestination = LatLng(viagem.latitudeDestino!!.toDouble(), viagem.longitudeDestino!!.toDouble())

        listDangerAreas.execute(googleMap!!)

        this.googleMap!!.addMarker(
            MarkerOptions()
                .position(latLngOrigin)
                .title(viagem.nomeOrigem)
                .icon(bitmapDescriptorFromVector(this, R.drawable.pin_location))
        )
        this.googleMap!!.addMarker(
            MarkerOptions()
                .position(latLngDestination)
                .title(viagem.nomeDestino)
                .icon(bitmapDescriptorFromVector(this, R.drawable.pin_saferoute))
        )
        this.googleMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngOrigin, 14.5f))


        val path: MutableList<List<LatLng>> = ArrayList()
        val urlDirections = "https://maps.googleapis.com/maps/api/directions/json?origin=${viagem.latitudeOrigem!!},${viagem.longitudeOrigem!!}&destination=${viagem.latitudeDestino!!},${viagem.longitudeDestino!!}&key=${BuildConfig.GOOGLE_MAPS_API_KEY}"
        val directionsRequest = object : StringRequest(Request.Method.GET, urlDirections, Response.Listener<String> {
                response ->
            val jsonResponse = JSONObject(response)
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

    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        return ContextCompat.getDrawable(context, vectorResId)?.run {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }

    override fun onStop() {
        super.onStop()
        ClearUserSession.execute(applicationContext)
    }
}
