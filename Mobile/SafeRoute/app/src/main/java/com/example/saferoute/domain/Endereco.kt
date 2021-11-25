package com.example.saferoute.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Endereco(
    var id: Int? = null,
    @SerializedName("descricao") var description: String? = null,
    @SerializedName("cep") var cep: String? = null,
    @SerializedName("logradouro") var publicPlace: String? = null,
    @SerializedName("numero") var number: String? = null,
    @SerializedName("estado") var state: String? = null
): Serializable

