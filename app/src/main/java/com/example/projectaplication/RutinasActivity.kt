package com.example.projectaplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.projectaplication.databinding.ActivityMainBinding

class RutinasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rutinas)

        val view = findViewById<View>(R.id.bgprogress)
        view.setOnClickListener{
            val intent = Intent(this, EjercitandoActivity::class.java)
            startActivity(intent)
        }

    }
}