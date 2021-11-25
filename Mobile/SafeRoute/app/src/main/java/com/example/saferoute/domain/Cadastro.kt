package com.example.saferoute.domain

import java.io.Serializable

data class Cadastro(
    var usuarioComum: UsuarioComum? = null,
    var endereco: Endereco? = null,
    var contatoConfianca: ContatoConfianca? = null,
    var documento: Documento? = null,
): Serializable
