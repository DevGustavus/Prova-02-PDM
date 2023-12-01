package com.example.prova02pdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.prova02pdm.databinding.ActivityMainBinding
import com.example.prova02pdm.telas.MenuActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, MenuActivity::class.java)

        binding.titulo.postDelayed({startActivity(intent)}, 2000)

        binding.titulo.postDelayed({finish()},2000)
    }
}