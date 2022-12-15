package com.example.projectaplication.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.projectaplication.utilitarios.Constantes
import java.util.concurrent.TimeUnit

object ClienteGym {

    private var okHttpClient = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        //.addInterceptor(ApiInterceptor()) //para usar el token en toda la consulta
        .build()

    private fun buildRetrofit() = Retrofit.Builder()
        .baseUrl(Constantes().API_PATITAS_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: GymServicio by lazy {
        buildRetrofit().create(GymServicio::class.java)
    }
}