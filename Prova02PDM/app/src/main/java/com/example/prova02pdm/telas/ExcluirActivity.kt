package com.example.prova02pdm.telas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.prova02pdm.R
import com.example.prova02pdm.databinding.ActivityExcluirBinding

class ExcluirActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExcluirBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExcluirBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = getColor(R.color.black)

        val listView = binding.listView

        val listaDeItens = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 2", "Item 3", "Item 4", "Item 2", "Item 3", "Item 4", "Item 2", "Item 3", "Item 4", "Item 2", "Item 3", "Item 4"    )

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaDeItens)

        listView.adapter = adapter

        binding.layoutBtn.setOnClickListener(){

        }

        binding.btnBack.setOnClickListener(){
            finish()
        }

    }
}