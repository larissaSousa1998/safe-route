package com.example.saferoute.endpoint

import com.example.saferoute.domain.Publicacao
import com.example.saferoute.response.PublicacaoResponse
import retrofit2.Call
import retrofit2.http.GET

interface PublicacaoEndpoints {
    @GET("publicacoes")
    fun getPublicacoes(): Call<PublicacaoResponse>
}