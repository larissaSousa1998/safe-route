package com.example.saferoute.endpoint

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface CadastroEndpoints {
//    @Headers("Content-Type: application/json")
//    @POST("usuarias")
//    fun postUsuario(@Body userData: UserLoginInfo): Call<UsuarioComum>

    @Multipart
    @POST("usuarias")
    fun cadastro(@Part cadastro: MultipartBody.Part,
                @Part selfie: MultipartBody.Part,
                @Part fotoFrente: MultipartBody.Part,
                @Part fotoVerso: MultipartBody.Part): Call<ResponseBody>
}