package com.example.saferoute.usecase

import android.graphics.Color
import com.example.saferoute.configuration.SafeRouteConstants
import com.example.saferoute.domain.AreaRisco
import com.example.saferoute.endpoint.AreaRiscoEndpoints
import com.example.saferoute.util.NetworkUtils
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CircleOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.android.gms.maps.model.LatLng

class ListDangerAreas {

    fun execute(googleMap: GoogleMap) {
        this.retrieveAndListDangerAreas(googleMap)
    }

    private fun retrieveAndListDangerAreas(googleMap: GoogleMap) {
        val retrofit = NetworkUtils.buildService(AreaRiscoEndpoints::class.java,
            SafeRouteConstants.SAFE_ROUTE_API_BASE_URL)
        retrofit.getAreasRisco().enqueue(
            object : Callback<List<AreaRisco>> {
                override fun onFailure(call: Call<List<AreaRisco>>, t: Throwable) {
                    throw t
                }
                override fun onResponse(call: Call<List<AreaRisco>>, response: Response<List<AreaRisco>>) {
                    val dangerAreas: List<AreaRisco>? = response.body()
                    if(!dangerAreas.isNullOrEmpty()) {
                        listAreasOnMap(googleMap, dangerAreas)
                    }
                }
            }
        )
    }

    private fun listAreasOnMap(googleMap: GoogleMap, areas: List<AreaRisco>) {
        areas.forEach {
            googleMap.addCircle(this.retrieveCircleOptions(it.latitude, it.longitude))
        }
    }

    private fun retrieveCircleOptions(latitude: String, longitude: String): CircleOptions {
        return CircleOptions()
            .center(LatLng(latitude.toDouble(), longitude.toDouble()))
            .radius(100.0)
            .strokeColor(Color.RED)
            .strokeWidth(1F)
            .fillColor(Color.parseColor("#7CFF0000"))
    }

}