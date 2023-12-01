package com.example.prova02pdm.telas

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.prova02pdm.R
import com.example.prova02pdm.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = getColor(R.color.black) // Substitua "sua_cor" pelo ID real da sua cor


        binding.layoutBtn1.setOnClickListener(){
            val intent = Intent(this, InserirActivity::class.java)
            startActivity(intent)
        }
        binding.layoutBtn2.setOnClickListener(){
            val intent = Intent(this, MostrarActivity::class.java)
            startActivity(intent)
        }
        binding.layoutBtn3.setOnClickListener(){
            val intent = Intent(this, AtualizarActivity::class.java)
            startActivity(intent)
        }
        binding.layoutBtn4.setOnClickListener(){
            val intent = Intent(this, ExcluirActivity::class.java)
            startActivity(intent)
        }
        binding.layoutBtn5.setOnClickListener(){
            val intent = Intent(this, TxtActivity::class.java)
            startActivity(intent)
        }
        binding.layoutBtn6.setOnClickListener(){
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
    }
}