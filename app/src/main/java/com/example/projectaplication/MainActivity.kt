package com.example.projectaplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import com.example.projectaplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),View.OnClickListener,
AdapterView.OnItemClickListener{

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Inicio())

        binding.bottomNavigationView.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.Inicio -> replaceFragment(Inicio())
                R.id.Coach -> replaceFragment(CoachFragment())
                R.id.Sede -> replaceFragment(SedeFragment())
                R.id.Sugerencias -> replaceFragment(Sugerencias())

                else -> {

                }

            }

            true

        }

    }

    private fun replaceFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()

    }

    override fun onClick(v: View) {

    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

}
