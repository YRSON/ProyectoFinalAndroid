package com.example.projectaplication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectaplication.databinding.FragmentInicioBinding

class Inicio : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //this code error

        val bind = FragmentInicioBinding.inflate(layoutInflater)

        bind.imgRutinas.setOnClickListener{
            val intent = Intent (this@Inicio.requireContext(), SeleccionRutinaActivity::class.java)
            startActivity(intent)
        }

        bind.floatingActionButton.setOnClickListener{
            val intent = Intent (this@Inicio.requireContext(), SoporteActivity::class.java)
            startActivity(intent)
        }
        return bind.root
    }

}