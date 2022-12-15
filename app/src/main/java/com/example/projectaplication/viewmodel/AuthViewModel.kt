package com.example.projectaplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.projectaplicatio.retrofit.response.ResponseRegistro
import com.example.projectaplication.repository.AuthRepository
import com.example.projectaplication.retrofit.request.RequestLogin
import com.example.projectaplication.retrofit.request.RequestRegistro
import com.example.projectaplication.retrofit.response.ResponseLogin

class AuthViewModel: ViewModel() {

    var responseLogin: LiveData<ResponseLogin>
    var responseRegistro: LiveData<ResponseRegistro>
    private var repository = AuthRepository()

    init {
        responseLogin = repository.loginResponse
        responseRegistro = repository.registroRespose
    }

    fun autenticarUsuario(usuario: String, password: String) {
        responseLogin = repository.autenticarUsuario(
            RequestLogin(usuario, password)
        )
    }

    fun registroUsuario(nombres: String, apellidos: String,
    email: String,
        celular: String, usuario: String, password: String) {
        responseRegistro = repository.registrarUsuario(
            RequestRegistro(nombres, apellidos, email,
                celular, usuario, password)
        )
    }

}