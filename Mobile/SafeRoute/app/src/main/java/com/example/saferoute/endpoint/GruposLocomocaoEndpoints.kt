package com.example.saferoute.endpoint

import com.example.saferoute.domain.GroupoLocomocaoUsuaria
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GruposLocomocaoEndpoints {

    @GET("grupos-locomocao/participante/{id}")
    fun retrieveGroupsByUser(@Path("id") id: Int): Call<List<GroupoLocomocaoUsuaria>>

}