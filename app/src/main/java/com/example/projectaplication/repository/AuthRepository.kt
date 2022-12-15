package com.example.projectaplication.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.projectaplicatio.retrofit.response.ResponseRegistro
import com.example.projectaplication.retrofit.request.RequestLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.projectaplication.retrofit.ClienteGym
import com.example.projectaplication.retrofit.request.RequestRegistro
import com.example.projectaplication.retrofit.response.ResponseLogin

class AuthRepository {
    
    var loginResponse = MutableLiveData<ResponseLogin>()
    var registroRespose = MutableLiveData<ResponseRegistro>()

    fun autenticarUsuario(requestLogin: RequestLogin)
    : MutableLiveData<ResponseLogin> {
        val call: Call<ResponseLogin> =
            ClienteGym.retrofitService.login(requestLogin)
        call.enqueue(object: Callback<ResponseLogin>{
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                loginResponse.value = response.body()
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                Log.e("ErrorLoing", t.message.toString())
            }
        })
        return loginResponse
    }

    fun registrarUsuario(requestRegistro: RequestRegistro)
    : MutableLiveData<ResponseRegistro> {
        val call: Call<ResponseRegistro>
        = ClienteGym.retrofitService.registro(requestRegistro)
        call.enqueue(object: Callback<ResponseRegistro>{
            override fun onResponse(
                call: Call<ResponseRegistro>,
                response: Response<ResponseRegistro>
            ) {
                registroRespose.value = response.body()
            }

            override fun onFailure(call: Call<ResponseRegistro>, t: Throwable) {
                Log.e("ErrorLoing", t.message.toString())
            }
        })
        return registroRespose
    }
}