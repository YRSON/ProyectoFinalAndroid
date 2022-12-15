package com.example.projectaplication.retrofit.response

data class ResponseLogin(
    var rpta: Boolean,
    var idpersona: String,
    var nombres: String,
    var apellidos: String,
    var email: String,
    //var direccion: String,
    var celular: String,
    var usuario: String,
    var password: String,
    var mensaje: String
)
