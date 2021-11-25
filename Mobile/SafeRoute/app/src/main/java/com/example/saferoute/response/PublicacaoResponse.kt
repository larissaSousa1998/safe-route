package com.example.saferoute.response

import com.example.saferoute.domain.Publicacao
import com.google.gson.annotations.SerializedName

data class PublicacaoResponse(
    @SerializedName("pilha")
    val publicacaoes: List<Publicacao>
)
