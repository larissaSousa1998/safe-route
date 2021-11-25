package com.example.saferoute.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

 data class ContatoConfianca(
     var id: Int? = null,
     @SerializedName("nome") var trustContactName : String?= null,
     @SerializedName("email") var trustContatEmail : String?= null,
     @SerializedName("numeroTelefone") var trustContactCelPhone: String?= null
 ): Serializable