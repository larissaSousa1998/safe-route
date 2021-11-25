package com.example.saferoute.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DestinoPrevio (
    @SerializedName("descricao")
    var descricao:String,

    @JsonIgnore
    var origem:String?,

    @SerializedName("logradouro")
    var logradouro:String,

    @SerializedName("numero")
    var numero: String,

    @SerializedName("cep")
    var cep: String,

    @SerializedName("estado")
    var estado: String,

    @SerializedName("idUsuaria")
    var idUsuaria: Int
    ): Serializable