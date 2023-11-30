package com.example.prova02pdm.telas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.prova02pdm.R
import com.example.prova02pdm.databinding.ActivityTxtBinding

class TxtActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTxtBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTxtBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = getColor(R.color.black)

        binding.btn1.setOnClickListener(){

        }
        binding.btn2.setOnClickListener(){

        }
        binding.btnBack.setOnClickListener(){
            finish()
        }
    }
}