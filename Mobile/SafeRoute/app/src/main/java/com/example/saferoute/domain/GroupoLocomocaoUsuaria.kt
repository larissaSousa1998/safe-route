package com.example.saferoute.domain

import java.io.Serializable

data class GroupoLocomocaoUsuaria(
   val nome: String,
   val title: String,
   val viagem: Viagem
) : Serializable

