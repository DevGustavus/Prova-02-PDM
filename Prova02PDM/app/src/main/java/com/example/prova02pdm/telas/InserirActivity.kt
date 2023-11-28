package com.example.prova02pdm.telas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.prova02pdm.R
import com.example.prova02pdm.databinding.ActivityInserirBinding

class InserirActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInserirBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInserirBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.layoutBtn1.setOnClickListener(){
            binding.popUp1.visibility = View.VISIBLE

            binding.btnInserirImovel.setOnClickListener(){

                val matricula = binding.editTextMatricula.text.toString()
                val endereco = binding.editTextEndereco.text.toString()
                val aluguel = binding.editTextAluguel.text.toString().toFloat()

            }
            binding.btnCancelarImovel.setOnClickListener(){
                binding.popUp1.visibility = View.INVISIBLE
            }
        }

        binding.layoutBtn2.setOnClickListener(){
            binding.popUp2.visibility = View.VISIBLE

            binding.btnInserirProprietario.setOnClickListener(){

                val cpf = binding.editTextCPFprop.text.toString()
                val nome = binding.editTextNomeprop.text.toString()
                val email = binding.editTextEmail.text.toString()

            }
            binding.btnCancelarProprietario.setOnClickListener(){
                binding.popUp2.visibility = View.INVISIBLE
            }
        }

        binding.layoutBtn3.setOnClickListener(){
            binding.popUp3.visibility = View.VISIBLE

            binding.btnInserirInquilino.setOnClickListener(){

                val cpf = binding.editTextCPFinq.text.toString()
                val nome = binding.editTextNomeinq.text.toString()
                val caucao = binding.editTextCaucao.text.toString().toFloat()

            }
            binding.btnCancelarInquilino.setOnClickListener(){
                binding.popUp3.visibility = View.INVISIBLE
            }
        }

        binding.layoutBtn4.setOnClickListener(){

        }

        binding.btnBack.setOnClickListener(){
            finish()
        }

    }
}