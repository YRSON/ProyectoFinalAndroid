package com.example.projectaplication.utilitarios

import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.example.projectaplication.R
import com.example.projectaplication.commom.MiApp
import com.example.projectaplication.commom.TipoMensaje
import kotlin.reflect.KParameter

object AppMensaje {

    fun enviarMensaje(vista: View, mensaje: String, tipo: com.example.projectaplication.utilitarios.TipoMensaje){
        val snackbar = Snackbar.make(vista, mensaje, Snackbar.LENGTH_LONG)
        val snackbarView: View = snackbar.view
        if(tipo == com.example.projectaplication.utilitarios.TipoMensaje.ERROR){
            snackbarView.setBackgroundColor(
                ContextCompat.getColor(
                    MiApp.instance,
                    R.color.snackbarerror)
            )
        }else{
            snackbarView.setBackgroundColor(
                ContextCompat.getColor(MiApp.instance,
                    R.color.snackbarexito)
            )
        }
        snackbar.show()
    }
}