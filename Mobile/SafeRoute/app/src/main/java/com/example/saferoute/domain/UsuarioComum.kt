package com.example.saferoute.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UsuarioComum (
    var id: String? = null,
    @SerializedName("nome") var name: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("senha") var password: String? = null,
    @SerializedName("dataNascimento") var birthDate: String? = null,
    var dataCadastro: String? = null,
    var enderecos: List<Endereco>? = null,
    var contatosConfianca: List<ContatoConfianca>? = null,
    var documento: Documento? = null,
    var gruposLocomocao: String? = null,
    var denunciaLocal: String? = null,
    var publicacao: String? = null,
    var numeroTelefone: String? = null,
    var selfie: ByteArray? = null,
    var foto: String? = null,
    @SerializedName("aprovada") var isAprovada: Boolean? = false,
    @SerializedName("ativa") var isAtiva: Boolean? = false,
    var receberDicas: Boolean? = false
) : Serializable