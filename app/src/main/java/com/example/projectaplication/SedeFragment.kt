package com.example.projectaplication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.projectaplication.databinding.FragmentInicioBinding
import com.example.projectaplication.databinding.FragmentSedeBinding

class SedeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //this code error

        val bind = FragmentSedeBinding.inflate(layoutInflater)

        bind.imgSanMiguel.setOnClickListener{
            val intent = Intent (this@SedeFragment.requireContext(), SedeSanMiguelActivity::class.java)
            startActivity(intent)
        }

        bind.imgLima.setOnClickListener{
            val intent = Intent (this@SedeFragment.requireContext(), LimaActivity::class.java)
            startActivity(intent)
        }

        bind.imgMiraflores.setOnClickListener{
            val intent = Intent (this@SedeFragment.requireContext(), MirafloresActivity::class.java)
            startActivity(intent)
        }

        bind.imgSjl.setOnClickListener{
            val intent = Intent (this@SedeFragment.requireContext(), SjlActivity::class.java)
            startActivity(intent)
        }

        return bind.root
    }
}