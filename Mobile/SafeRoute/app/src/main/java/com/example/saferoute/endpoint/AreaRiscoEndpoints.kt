package com.example.saferoute.endpoint

import com.example.saferoute.domain.AreaRisco
import retrofit2.Call
import retrofit2.http.GET

interface AreaRiscoEndpoints {

    @GET("areas-risco")
    fun getAreasRisco(): Call<List<AreaRisco>>
}