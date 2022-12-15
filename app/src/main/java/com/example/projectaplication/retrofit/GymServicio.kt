package com.example.projectaplication.retrofit

import com.example.projectaplicatio.retrofit.response.ResponseRegistro
import com.example.projectaplication.retrofit.request.RequestLogin
import com.example.projectaplication.retrofit.request.RequestRegistro
import com.example.projectaplication.retrofit.response.ResponseLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT


interface GymServicio {

    @POST("login.php")
    fun login(@Body requestLogin: RequestLogin): Call<ResponseLogin>

    @PUT("persona.php")
    fun registro(@Body requestRegistro: RequestRegistro): Call<ResponseRegistro>



}