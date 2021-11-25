package com.example.saferoute.endpoint;

import com.example.saferoute.domain.DestinoPrevio;
import com.example.saferoute.domain.Endereco

import retrofit2.Call;
import retrofit2.http.*

interface DestinoPrevioEndpoints {

    @GET("enderecos/usuaria/{id}")
    fun retrievePreviousDestinationsbyUser(@Path("id") id: Int): Call<List<DestinoPrevio>>

    @Headers("Content-Type: application/json")
    @POST("enderecos")
    fun createPreviousDestination(@Body destinoPrevio: DestinoPrevio): Call<Void>
}
