package com.example.projectaplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.projectaplicatio.retrofit.response.ResponseRegistro
import com.example.projectaplication.databinding.ActivityRegistroBinding
import com.example.projectaplication.utilitarios.AppMensaje
import com.example.projectaplication.utilitarios.TipoMensaje
import com.example.projectaplication.viewmodel.AuthViewModel

class RegistroActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityRegistroBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authViewModel = ViewModelProvider(this)
            .get(AuthViewModel::class.java)
        binding.btnregistrarme.setOnClickListener(this)
        binding.btnirlogin.setOnClickListener(this)

        authViewModel.responseRegistro.observe(this, Observer {
                response -> obtenerResultadoRegistro(response)
        })
    }

    private fun obtenerResultadoRegistro(response: ResponseRegistro) {
        if(response.rpta) {
            setearControles()
            AppMensaje.enviarMensaje(binding.root,
                response.mensaje, TipoMensaje.EXITO)
        } else {
            AppMensaje.enviarMensaje(binding.root,
                response.mensaje, TipoMensaje.ERROR)
        }
        binding.btnregistrarme.isEnabled = true
        binding.btnirlogin.isEnabled = true
    }

    override fun onClick(vista: View) {
        when(vista.id){
            R.id.btnregistrarme -> registrarUsuario()
            R.id.btnirlogin -> startActivity(
                Intent(applicationContext, LoginActivity::class.java)
            )
        }
    }

    private fun registrarUsuario() {
        binding.btnregistrarme.isEnabled = false
        binding.btnirlogin.isEnabled = false
        if(validarFormulario()){
            authViewModel.registroUsuario(binding.etnomusuario.text.toString(),
                binding.etapeusuario.text.toString(), binding.etemailusuario.text.toString(),
                binding.etcelusuario.text.toString(), binding.etusureg.text.toString(),
                binding.etpassreg.text.toString())
        }else{
            binding.btnregistrarme.isEnabled = true
            binding.btnirlogin.isEnabled = true
        }
    }

    private fun setearControles() {
        binding.etnomusuario.setText("")
        binding.etapeusuario.setText("")
        binding.etemailusuario.setText("")
        binding.etcelusuario.setText("")
        binding.etusureg.setText("")
        binding.etpassreg.setText("")
        binding.etrepassreg.setText("")
    }

    private fun validarFormulario() : Boolean {
        var respuesta = true
        var mensaje = ""
        when {
            binding.etnomusuario.text.toString().trim().isEmpty() -> {
                binding.etnomusuario.isFocusableInTouchMode = true
                binding.etnomusuario.requestFocus()
                mensaje = "Ingrese su nombre"
                respuesta = false
            }
            binding.etapeusuario.text.toString().trim().isEmpty() -> {
                binding.etapeusuario.isFocusableInTouchMode = true
                binding.etapeusuario.requestFocus()
                mensaje = "Ingrese su apellido"
                respuesta = false
            }
            binding.etemailusuario.text.toString().trim().isEmpty() -> {
                binding.etemailusuario.isFocusableInTouchMode = true
                binding.etemailusuario.requestFocus()
                mensaje = "Ingrese su email"
                respuesta = false
            }
            binding.etcelusuario.text.toString().trim().isEmpty() -> {
                binding.etcelusuario.isFocusableInTouchMode = true
                binding.etcelusuario.requestFocus()
                mensaje = "Ingrese su celular"
                respuesta = false
            }
            binding.etusureg.text.toString().trim().isEmpty() -> {
                binding.etusureg.isFocusableInTouchMode = true
                binding.etusureg.requestFocus()
                mensaje = "Ingrese su cuenta de usuario"
                respuesta = false
            }
            binding.etpassreg.text.toString().trim().isEmpty() -> {
                binding.etpassreg.isFocusableInTouchMode = true
                binding.etpassreg.requestFocus()
                mensaje = "Ingrese su password"
                respuesta = false
            }
            binding.etrepassreg.text.toString().trim().isNotEmpty() -> {
                if (binding.etpassreg.text.toString() != binding.etrepassreg.text.toString()) {
                    binding.etrepassreg.isFocusableInTouchMode = true
                    binding.etrepassreg.requestFocus()
                    mensaje = "Password no coincide"
                    respuesta = false
                }
            }
        }
        if (!respuesta) AppMensaje.enviarMensaje(
            binding.root, mensaje,
            TipoMensaje.ERROR
        )
        return respuesta
    }
}