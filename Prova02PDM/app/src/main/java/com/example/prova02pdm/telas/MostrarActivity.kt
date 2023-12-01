package com.example.prova02pdm.telas

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.example.prova02pdm.DAO.LocacaoDAO
import com.example.prova02pdm.R
import com.example.prova02pdm.banco.MyDataBaseHelper
import com.example.prova02pdm.databinding.ActivityMostrarBinding

class MostrarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMostrarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMostrarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val locacaoDAO = LocacaoDAO(MyDataBaseHelper(applicationContext))

        window.statusBarColor = getColor(R.color.black)

        val listView = binding.listView

        val lista = locacaoDAO.retornarListaLocacao()

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lista)

        listView.adapter = adapter

        Log.i("lista", lista.toString())

        binding.btnBack.setOnClickListener(){
            finish()
        }
        
    }
}