package com.example.saferoute.endpoint

import com.example.saferoute.domain.User
import com.example.saferoute.domain.UserLoginInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserEndpoints {

    @Headers("Content-Type: application/json")
    @POST("usuarias/login")
    fun login(@Body userData: UserLoginInfo): Call<User>

}