package com.example.saferoute.domain

import com.google.gson.annotations.SerializedName

data class UserLoginInfo (
    @SerializedName("email") val userLogin: String?,
    @SerializedName("senha") val userPassword: String?
)