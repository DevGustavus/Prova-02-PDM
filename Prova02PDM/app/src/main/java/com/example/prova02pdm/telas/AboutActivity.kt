package com.example.prova02pdm.telas

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.prova02pdm.R
import com.example.prova02pdm.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = getColor(R.color.black)

        binding.btn1.setOnClickListener(){
            val url = "https://github.com/c-Alvinn"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        binding.btn2.setOnClickListener(){
            val url = "https://github.com/DevGustavus"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        binding.btnBack.setOnClickListener(){
            finish()
        }
    }
}