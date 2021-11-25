package com.example.saferoute.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable

data class Viagem (
    @JsonIgnore
    var nomeOrigem: String? = null,
    var latitudeOrigem: String? = null,
    var longitudeOrigem: String? = null,
    @JsonIgnore
    var nomeDestino: String? = null,
    var latitudeDestino: String? = null,
    var longitudeDestino: String? = null,
): Serializable
