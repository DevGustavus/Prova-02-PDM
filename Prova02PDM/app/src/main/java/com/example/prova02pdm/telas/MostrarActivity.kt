package com.example.prova02pdm.telas

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.prova02pdm.R
import com.example.prova02pdm.databinding.ActivityMostrarBinding

class MostrarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMostrarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMostrarBinding.inflate(layoutInflater)
        setContentView(binding.root)


        window.statusBarColor = getColor(R.color.black)

        val listView = binding.listView

        val listaDeItens = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 2", "Item 3", "Item 4", "Item 2", "Item 3", "Item 4", "Item 2", "Item 3", "Item 4", "Item 2", "Item 3", "Item 4"    )

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaDeItens)

        listView.adapter = adapter

        binding.btnBack.setOnClickListener(){
            finish()
        }
        
    }
}