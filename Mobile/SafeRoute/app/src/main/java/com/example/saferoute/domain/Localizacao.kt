package com.example.saferoute.domain

import java.io.Serializable

data class Localizacao(
    var nome: String? = null,
    var latitude: String?= null,
    var longitude: String? = null
): Serializable