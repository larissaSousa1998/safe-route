package com.example.saferoute.endpoint

import com.example.saferoute.response.ViaCepResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ViaCepEndpoints {

    @GET("{cep}/json")
    fun search(@Path("cep") cep: String): Call<ViaCepResponse>

}