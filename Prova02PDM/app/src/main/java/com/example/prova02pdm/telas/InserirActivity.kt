package com.example.prova02pdm.telas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.prova02pdm.R
import com.example.prova02pdm.databinding.ActivityInserirBinding

class InserirActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInserirBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInserirBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.layoutBtnLocacao.setOnClickListener(){
            binding.popUpSelect.visibility = View.VISIBLE

            binding.layoutBtnImovel.setOnClickListener(){
                imovelPopUp()
            }
            binding.layoutBtnProprietario.setOnClickListener(){
                proprietarioPopUp()
            }
            binding.layoutBtnInquilino.setOnClickListener(){
                inquilinoPopUp()
            }
            binding.btnInserir.setOnClickListener(){
                //valida se tem tudo e joga no banco de dados

                binding.popUpSelect.visibility = View.INVISIBLE
            }
            binding.btnCancelarInsercao.setOnClickListener(){
                //faz as variáveis zerar
                binding.popUpSelect.visibility = View.INVISIBLE
            }
        }

        binding.btnBack.setOnClickListener(){
            finish()
        }

    }

    private fun fecharPopUp(){

        binding.popUpInput.visibility = View.INVISIBLE

        binding.tituloPopUp.text = ""
        binding.editTextFirst.hint = ""
        binding.editTextSecond.hint = ""
        binding.editTextFloat.hint = ""
        binding.editTextFirst.text.clear()
        binding.editTextSecond.text.clear()
        binding.editTextFloat.text.clear()

        if (binding.editTextEmail.visibility == View.INVISIBLE) {
            binding.editTextEmail.visibility = View.VISIBLE
        }

        if (binding.editTextFloat.visibility == View.INVISIBLE) {
            binding.editTextFloat.visibility = View.VISIBLE
        }


    }

    private fun imovelPopUp(){

        binding.popUpInput.visibility = View.VISIBLE

        binding.tituloPopUp.text = "Inserir Imóvel"
        binding.editTextFirst.hint = "Matrícula"
        binding.editTextSecond.hint = "Endereço"
        binding.editTextEmail.visibility = View.INVISIBLE
        binding.editTextFloat.hint = "Valor do Alguel"

        binding.btnOk.setOnClickListener(){
            val matricula = binding.editTextFirst.text.toString()
            val endereco = binding.editTextSecond.text.toString()
            val aluguel = binding.editTextFloat.text.toString()

            fecharPopUp()
            // return imovel
        }
        binding.btnCancelar.setOnClickListener(){
            fecharPopUp()
            // return null
        }

    }

    private fun proprietarioPopUp(){

        binding.popUpInput.visibility = View.VISIBLE

        binding.tituloPopUp.text = "Inserir Proprietário"
        binding.editTextFirst.hint = "CPF"
        binding.editTextSecond.hint = "Nome"
        binding.editTextFloat.visibility = View.INVISIBLE

        binding.btnOk.setOnClickListener(){
            val cpf = binding.editTextFirst.text.toString()
            val nome = binding.editTextSecond.text.toString()
            val email = binding.editTextEmail.text.toString()

            fecharPopUp()
            // return proprietario
        }
        binding.btnCancelar.setOnClickListener(){
            fecharPopUp()
            // return null
        }

    }

    private fun inquilinoPopUp(){

        binding.popUpInput.visibility = View.VISIBLE

        binding.tituloPopUp.text = "Inserir Inquilino"
        binding.editTextFirst.hint = "CPF"
        binding.editTextSecond.hint = "Nome"
        binding.editTextEmail.visibility = View.INVISIBLE
        binding.editTextFloat.hint = "Valor do Caução Depositado"

        binding.btnOk.setOnClickListener(){
            val cpf = binding.editTextFirst.text.toString()
            val nome = binding.editTextSecond.text.toString()
            val caucao = binding.editTextFloat.text.toString()

            Log.i("teste", cpf+" "+nome+" "+caucao)

            fecharPopUp()
            // return inquilino
        }
        binding.btnCancelar.setOnClickListener(){
            fecharPopUp()
            // return null
        }

    }
}