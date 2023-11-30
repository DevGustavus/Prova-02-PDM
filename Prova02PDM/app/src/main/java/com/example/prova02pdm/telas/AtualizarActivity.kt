package com.example.prova02pdm.telas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import com.example.prova02pdm.DAO.ImovelDAO
import com.example.prova02pdm.DAO.InquilinoDAO
import com.example.prova02pdm.DAO.LocacaoDAO
import com.example.prova02pdm.DAO.ProprietarioDAO
import com.example.prova02pdm.R
import com.example.prova02pdm.banco.MyDataBaseHelper
import com.example.prova02pdm.classes.Imovel
import com.example.prova02pdm.classes.Inquilino
import com.example.prova02pdm.classes.Locacao
import com.example.prova02pdm.classes.Proprietario
import com.example.prova02pdm.databinding.ActivityAtualizarBinding

class AtualizarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAtualizarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtualizarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = getColor(R.color.black)

        val listView = binding.listView

        val listaDeItens = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 2", "Item 3", "Item 4", "Item 2", "Item 3", "Item 4", "Item 2", "Item 3", "Item 4", "Item 2", "Item 3", "Item 4"    )

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaDeItens)

        listView.adapter = adapter

        val daoImovel = ImovelDAO(MyDataBaseHelper(applicationContext))
        val daoProp = ProprietarioDAO(MyDataBaseHelper(applicationContext))
        val daoInqui = InquilinoDAO(MyDataBaseHelper(applicationContext))
        val daoLocacao = LocacaoDAO(MyDataBaseHelper(applicationContext))

        var id: Int = 0

        var imovel: Imovel? = null
        var proprietario : Proprietario? = null
        var inquilino : Inquilino? = null

        binding.layoutBtn.setOnClickListener(){

            id = binding.editTextId.text.toString().toInt()

            binding.popUpSelect.visibility = View.VISIBLE

            binding.layoutBtnImovel.setOnClickListener(){

                toggleItens()

                imovelPopUp(imovel)

                binding.btnOk.setOnClickListener(){
                    val matricula = binding.editTextFirst.text.toString()
                    val endereco = binding.editTextSecond.text.toString()
                    val aluguel = binding.editTextFloat.text.toString()

                    if (matricula == "" || endereco == "" || aluguel == ""){
                        binding.textPopUp.text = "Preencha todos os campos."
                        binding.popUp.visibility = View.VISIBLE
                        binding.popUp.postDelayed({binding.popUp.visibility = View.GONE}, 2000)
                        binding.popUp.postDelayed({binding.textPopUp.text = ""}, 2005)
                    }else {
                        imovel = Imovel(matricula, endereco, aluguel.toFloat())
                        fecharPopUp()

                        toggleItens()
                    }

                }
                binding.btnCancelar.setOnClickListener(){
                    fecharPopUp()

                    toggleItens()
                }
            }
            binding.layoutBtnProprietario.setOnClickListener(){

                toggleItens()

                proprietarioPopUp(proprietario)

                binding.btnOk.setOnClickListener(){
                    val cpf = binding.editTextFirst.text.toString()
                    val nome = binding.editTextSecond.text.toString()
                    val email = binding.editTextEmail.text.toString()

                    if (cpf == "" || nome == "" || email == ""){
                        binding.textPopUp.text = "Preencha todos os campos."
                        binding.popUp.visibility = View.VISIBLE
                        binding.popUp.postDelayed({binding.popUp.visibility = View.GONE}, 2000)
                        binding.popUp.postDelayed({binding.textPopUp.text = ""}, 2005)
                    }else {
                        proprietario = Proprietario(cpf, nome, email)
                        fecharPopUp()

                        toggleItens()
                    }

                }
                binding.btnCancelar.setOnClickListener(){
                    fecharPopUp()

                    toggleItens()
                }
            }
            binding.layoutBtnInquilino.setOnClickListener(){

                toggleItens()

                inquilinoPopUp(inquilino)

                binding.btnOk.setOnClickListener(){
                    val cpf = binding.editTextFirst.text.toString()
                    val nome = binding.editTextSecond.text.toString()
                    val caucao = binding.editTextFloat.text.toString()

                    if (cpf == "" || nome == "" || caucao == ""){
                        binding.textPopUp.text = "Preencha todos os campos."
                        binding.popUp.visibility = View.VISIBLE
                        binding.popUp.postDelayed({binding.popUp.visibility = View.GONE}, 2000)
                        binding.popUp.postDelayed({binding.textPopUp.text = ""}, 2005)
                    }else {
                        inquilino = Inquilino(cpf, nome, caucao.toFloat())
                        fecharPopUp()

                        toggleItens()
                    }
                }
                binding.btnCancelar.setOnClickListener(){
                    fecharPopUp()

                    toggleItens()
                }
            }

            binding.btnCancelarSelect.setOnClickListener(){
                imovel = null
                proprietario = null
                inquilino = null
                binding.popUpSelect.visibility = View.GONE
            }
        }

        binding.btnBack.setOnClickListener(){
            finish()
        }

    }

    private fun fecharPopUp(){

        binding.popUpInput.visibility = View.GONE

        binding.tituloPopUp.text = ""
        binding.editTextFirst.hint = ""
        binding.editTextSecond.hint = ""
        binding.editTextFloat.hint = ""
        binding.editTextFirst.text.clear()
        binding.editTextSecond.text.clear()
        binding.editTextFloat.text.clear()

        if (binding.editTextEmail.visibility == View.GONE) {
            binding.editTextEmail.visibility = View.VISIBLE
        }

        if (binding.editTextFloat.visibility == View.GONE) {
            binding.editTextFloat.visibility = View.VISIBLE
        }


    }

    private fun imovelPopUp(imovel : Imovel){

        binding.popUpInput.visibility = View.VISIBLE

        binding.tituloPopUp.text = "Atualizar Imóvel"
        binding.editTextFirst.hint = "Matrícula"
        binding.editTextFirst.setText(imovel.matricula)
        binding.editTextSecond.hint = "Endereço"
        binding.editTextSecond.setText(imovel.endereco)
        binding.editTextEmail.visibility = View.GONE
        binding.editTextFloat.hint = "Valor do Alguel"
        binding.editTextFloat.setText(imovel.valorAluguel.toString())

    }

    private fun proprietarioPopUp(proprietario: Proprietario){

        binding.popUpInput.visibility = View.VISIBLE

        binding.tituloPopUp.text = "IAtualizar Proprietário"
        binding.editTextFirst.hint = "CPF"
        binding.editTextFirst.setText(proprietario.CPF_prop)
        binding.editTextSecond.hint = "Nome"
        binding.editTextSecond.setText(proprietario.nome)
        binding.editTextEmail.setText(proprietario.email)
        binding.editTextFloat.visibility = View.GONE

    }

    private fun inquilinoPopUp(inquilino: Inquilino){

        binding.popUpInput.visibility = View.VISIBLE

        binding.tituloPopUp.text = "Atualizar Inquilino"
        binding.editTextFirst.hint = "CPF"
        binding.editTextFirst.setText(inquilino.CPF_inq)
        binding.editTextSecond.hint = "Nome"
        binding.editTextSecond.setText(inquilino.nome)
        binding.editTextEmail.visibility = View.GONE
        binding.editTextFloat.hint = "Valor do Caução Depositado"
        binding.editTextFloat.setText(inquilino.Valor_depositado.toString())

    }

    private fun toggleItens(){

        if(binding.layoutBtnImovel.visibility == View.VISIBLE){
            binding.layoutBtnImovel.visibility = View.GONE
        }else {
            binding.layoutBtnImovel.visibility = View.VISIBLE
        }
        if(binding.layoutBtnProprietario.visibility == View.VISIBLE){
            binding.layoutBtnProprietario.visibility = View.GONE
        }else {
            binding.layoutBtnProprietario.visibility = View.VISIBLE
        }
        if(binding.layoutBtnInquilino.visibility == View.VISIBLE){
            binding.layoutBtnInquilino.visibility = View.GONE
        }else {
            binding.layoutBtnInquilino.visibility = View.VISIBLE
        }
        if(binding.layoutBtns.visibility == View.VISIBLE){
            binding.layoutBtns.visibility = View.GONE
        }else {
            binding.layoutBtns.visibility = View.VISIBLE
        }
    }
}