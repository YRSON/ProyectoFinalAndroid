package com.example.projectaplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class SeleccionRutinaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleccion_rutina)

        val imageView = findViewById<ImageView>(R.id.imageView2)
        imageView.setOnClickListener{
            val intent = Intent(this, RutinasActivity::class.java)
            startActivity(intent)
        }
    }
}