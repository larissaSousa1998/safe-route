package com.example.saferoute.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Documento(
    var id: Int? = null,
    @SerializedName("tipo") var type: String? = null,
    @SerializedName("fotoFrente") var front: ByteArray? = null,
    @SerializedName("fotoVerso") var verse: ByteArray? = null,
    @SerializedName("numeracao") var numbering: String? = null
): Serializable