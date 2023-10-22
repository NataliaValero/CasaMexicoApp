package com.example.casamexicoapp.ui.mainMenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.casamexicoapp.R
import com.example.casamexicoapp.databinding.ActivityMain2Binding




class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =  ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment)
        // Find reference to bottom navigation view
        val navView = binding.bottomNavigationView
        // Hook yout navigation controller to bottom navigation view
        navView.setupWithNavController(navController)



    }
}