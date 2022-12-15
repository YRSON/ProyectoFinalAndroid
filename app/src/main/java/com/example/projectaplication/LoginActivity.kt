package com.example.projectaplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.projectaplication.R
import com.example.projectaplication.databinding.ActivityLoginBinding
import com.example.projectaplication.db.entity.PersonaEntity
import com.example.projectaplication.retrofit.response.ResponseLogin
import com.example.projectaplication.utilitarios.AppMensaje
import com.example.projectaplication.utilitarios.Constantes
import com.example.projectaplication.utilitarios.SharedPreferencesManager
import com.example.projectaplication.utilitarios.TipoMensaje
import com.example.projectaplication.viewmodel.AuthViewModel
import com.example.projectaplication.viewmodel.PersonaViewModel

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var authViewModel: AuthViewModel
    private lateinit var personaViewModel: PersonaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        personaViewModel = ViewModelProvider(this).get(PersonaViewModel::class.java)

        if(verificarCheckRecordarDatos()) {

            binding.etusuario.isEnabled = false
            binding.etpassword.isEnabled = false
            personaViewModel.obtener().observe(this, Observer { persona ->
                persona?.let {
                    binding.etusuario.setText(persona.usuario)
                    binding.etpassword.setText(persona.password)
                }
            })
        } else {
            personaViewModel.eliminarTodo()
        }

        binding.btnlogin.setOnClickListener(this)
        binding.textRegister.setOnClickListener(this)
        //interfaz observe -> cuando tenga informacion entrara a la funcion
        authViewModel.responseLogin.observe(this, Observer { response ->
            obtenerDatosLogin(response)
        })
    }
    private fun obtenerDatosLogin(response: ResponseLogin) {
        if (response.rpta) {
            val personaEntity = PersonaEntity(
                response.idpersona.toInt(), response.nombres,
                response.apellidos, response.email, response.celular,
                response.usuario, response.password
            )
            if(verificarCheckRecordarDatos()) {
                personaViewModel.actualizar(personaEntity)
            } else {
                personaViewModel.insertar(personaEntity)
                if(binding.chkrecordar.isChecked) {
                    SharedPreferencesManager().setSomeBooleanValue(Constantes().PREF_RECORDAR, true)
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            personaViewModel.insertar(personaEntity)
            finish()
        } else {
            AppMensaje.enviarMensaje(
                binding.root,
                response.mensaje, TipoMensaje.EXITO
            )
        }
        binding.textRegister.isEnabled = true
        binding.btnlogin.isEnabled = true
    }

    private fun verificarCheckRecordarDatos(): Boolean {
        return SharedPreferencesManager()
            .getSomeBooleanValue(Constantes().PREF_RECORDAR)
    }

    fun validarUsuarioPassword(): Boolean {
        var okValidacion = true
        if (binding.etusuario.text.toString().trim().isEmpty()) {
            binding.etusuario.isFocusableInTouchMode = true
            binding.etusuario.requestFocus()
            okValidacion = false
        } else if (binding.etpassword.text.toString().trim().isEmpty()) {
            binding.etpassword.isFocusableInTouchMode = true
            binding.etpassword.requestFocus()
            okValidacion = false
        }
        return okValidacion
    }

    override fun onClick(vista: View) {
        when (vista.id) {

            R.id.btnlogin->autenticarUsuario()
            R.id.textRegister -> startActivity(
                Intent(
                    applicationContext,
                    RegistroActivity::class.java
                )
            )
            R.id.chkrecordar -> setearValoresRecordar(vista)
        }
    }

    private fun setearValoresRecordar(vista: View) {
        if(vista is CheckBox) {
            val checked = vista.isChecked
            if(!checked) {
                if(verificarCheckRecordarDatos()) {
                    SharedPreferencesManager()
                        .deletePreferences(Constantes().PREF_RECORDAR)
                    personaViewModel.eliminarTodo()
                    binding.etusuario.isEnabled = true
                    binding.etpassword.isEnabled = true
                    binding.chkrecordar.text = "¿Recordar datos?"
                }
            }
        }
    }

    private fun autenticarUsuario() {
        binding.textRegister.isEnabled = false
        binding.btnlogin.isEnabled = false
        if (validarUsuarioPassword()) {
            authViewModel.autenticarUsuario(
                binding.etusuario.text.toString(),
                binding.etpassword.text.toString()
            )
        } else {
            AppMensaje.enviarMensaje(
                binding.root,
                getString(R.string.msgloginincompleto),
                TipoMensaje.EXITO
            )
            binding.textRegister.isEnabled = true
            binding.btnlogin.isEnabled = true
        }
    }

}