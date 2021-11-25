package com.example.saferoute.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnore
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class User(
    @PrimaryKey
    var id: Int? = null,

    @SerializedName("nome")
    @ColumnInfo(name = "name")
    var name: String? = null,

    @SerializedName("email")
    @ColumnInfo(name = "email")
    var email: String? = null,

    @SerializedName("senha")
    @ColumnInfo(name = "password")
    var password: String? = null,

    @SerializedName("dataNascimento")
    @ColumnInfo(name = "birth_date")
    var birthDate: String? = null,

    @JsonIgnore
    @ColumnInfo(name = "save_session")
    var saveSession: Boolean = false
): Serializable