package com.example.projectaplication

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.ListFragment
import com.example.projectaplication.databinding.ActivityMainBinding


class Sugerencias : Fragment() {

    interface RegistroSugerencia {
        fun AnotarSugerencia(position: Int)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sugerencias, container, false)
        val nextBtn: Button = view.findViewById(R.id.btnvisualizar)
        nextBtn.setOnClickListener {
            val fragment = ListaSugerencia()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.frame_layout, fragment)?.commit()
        }
        return view
    }

}