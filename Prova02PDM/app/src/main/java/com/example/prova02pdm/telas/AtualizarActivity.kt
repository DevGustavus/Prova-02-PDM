package com.example.prova02pdm.telas

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
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

        val daoImovel = ImovelDAO(MyDataBaseHelper(applicationContext))
        val daoProp = ProprietarioDAO(MyDataBaseHelper(applicationContext))
        val daoInqui = InquilinoDAO(MyDataBaseHelper(applicationContext))
        val daoLocacao = LocacaoDAO(MyDataBaseHelper(applicationContext))

        window.statusBarColor = getColor(R.color.black)

        val listView = binding.listView

        var lista = daoLocacao.retornarListaLocacao()

        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lista)

        listView.adapter = adapter



        var id: Int = 0

        var imovel: Imovel? = null
        var proprietario : Proprietario? = null
        var inquilino : Inquilino? = null

        binding.layoutBtn.setOnClickListener(){

            fecharTeclado()

            if (binding.editTextId.text.toString() != ""){
                id = binding.editTextId.text.toString().toInt()
                try {
                    imovel = daoImovel.retornarImovel(id)
                    proprietario = daoProp.retornarProprietario(id)
                    inquilino = daoInqui.retornarInquilino(id)
                    if (imovel != null && proprietario != null && inquilino != null){
                        imovel!!.id = id
                        proprietario!!.id = id
                        inquilino!!.id = id
                        binding.popUpSelect.visibility = View.VISIBLE
                    }else{
                        binding.textPopUp.text = "Id inválido!"
                        binding.popUp.visibility = View.VISIBLE
                        binding.popUp.postDelayed({binding.popUp.visibility = View.GONE}, 2000)
                        binding.popUp.postDelayed({binding.textPopUp.text = ""}, 2005)
                    }
                }catch (e: Exception){
                    binding.textPopUp.text = "Id inválido!"
                    binding.popUp.visibility = View.VISIBLE
                    binding.popUp.postDelayed({binding.popUp.visibility = View.GONE}, 2000)
                    binding.popUp.postDelayed({binding.textPopUp.text = ""}, 2005)
                }
            }else {
                binding.textPopUp.text = "Preencha o campo."
                binding.popUp.visibility = View.VISIBLE
                binding.popUp.postDelayed({binding.popUp.visibility = View.GONE}, 2000)
                binding.popUp.postDelayed({binding.textPopUp.text = ""}, 2005)
            }



            binding.layoutBtnImovel.setOnClickListener(){

                toggleItens()

                imovelPopUp(imovel!!)

                binding.btnOk.setOnClickListener(){

                    fecharTeclado()

                    val matricula = binding.editTextFirst.text.toString()
                    val endereco = binding.editTextSecond.text.toString()
                    val aluguel = binding.editTextFloat.text.toString()

                    if (matricula == "" || endereco == "" || aluguel == ""){
                        binding.textPopUp.text = "Preencha todos os campos."
                        binding.popUp.visibility = View.VISIBLE
                        binding.popUp.postDelayed({binding.popUp.visibility = View.GONE}, 2000)
                        binding.popUp.postDelayed({binding.textPopUp.text = ""}, 2005)
                    }else {
                        try {
                            imovel = Imovel(matricula, endereco, aluguel.toFloat())
                            imovel!!.id = id
                            daoImovel.atualizarImovel(imovel!!)
                            fecharPopUp()
                            toggleItens()

                            lista = daoLocacao.retornarListaLocacao()
                            adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lista)
                            listView.adapter = adapter

                            binding.textPopUp.text = "Atualizado com sucesso!"
                            binding.popUp.visibility = View.VISIBLE
                            binding.popUp.postDelayed({binding.popUp.visibility = View.GONE}, 2000)
                            binding.popUp.postDelayed({binding.textPopUp.text = ""}, 2005)
                        }catch (e: Exception){
                            binding.textPopUp.text = "Erro ao atualizar.\nTente novamente."
                            binding.popUp.visibility = View.VISIBLE
                            binding.popUp.postDelayed({binding.popUp.visibility = View.GONE}, 2000)
                            binding.popUp.postDelayed({binding.textPopUp.text = ""}, 2005)
                        }

                    }

                }
                binding.btnCancelar.setOnClickListener(){
                    fecharTeclado()
                    fecharPopUp()
                    toggleItens()
                }
            }
            binding.layoutBtnProprietario.setOnClickListener(){

                toggleItens()

                proprietarioPopUp(proprietario!!)

                binding.btnOk.setOnClickListener(){

                    fecharTeclado()

                    val cpf = binding.editTextFirst.text.toString()
                    val nome = binding.editTextSecond.text.toString()
                    val email = binding.editTextEmail.text.toString()

                    if (cpf == "" || nome == "" || email == ""){
                        binding.textPopUp.text = "Preencha todos os campos."
                        binding.popUp.visibility = View.VISIBLE
                        binding.popUp.postDelayed({binding.popUp.visibility = View.GONE}, 2000)
                        binding.popUp.postDelayed({binding.textPopUp.text = ""}, 2005)
                    }else {
                        try {
                            proprietario = Proprietario(cpf, nome, email)
                            proprietario!!.id = id
                            daoProp.atualizarProprietario(proprietario!!)
                            fecharPopUp()
                            toggleItens()

                            lista = daoLocacao.retornarListaLocacao()
                            adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lista)
                            listView.adapter = adapter

                            binding.textPopUp.text = "Atualizado com sucesso!"
                            binding.popUp.visibility = View.VISIBLE
                            binding.popUp.postDelayed({binding.popUp.visibility = View.GONE}, 2000)
                            binding.popUp.postDelayed({binding.textPopUp.text = ""}, 2005)
                        }catch (e: Exception){
                            binding.textPopUp.text = "Erro ao atualizar.\nTente novamente."
                            binding.popUp.visibility = View.VISIBLE
                            binding.popUp.postDelayed({binding.popUp.visibility = View.GONE}, 2000)
                            binding.popUp.postDelayed({binding.textPopUp.text = ""}, 2005)
                        }

                    }

                }
                binding.btnCancelar.setOnClickListener(){
                    fecharTeclado()
                    fecharPopUp()
                    toggleItens()
                }
            }
            binding.layoutBtnInquilino.setOnClickListener(){

                toggleItens()

                inquilinoPopUp(inquilino!!)

                binding.btnOk.setOnClickListener(){

                    fecharTeclado()

                    val cpf = binding.editTextFirst.text.toString()
                    val nome = binding.editTextSecond.text.toString()
                    val caucao = binding.editTextFloat.text.toString()

                    if (cpf == "" || nome == "" || caucao == ""){
                        binding.textPopUp.text = "Preencha todos os campos."
                        binding.popUp.visibility = View.VISIBLE
                        binding.popUp.postDelayed({binding.popUp.visibility = View.GONE}, 2000)
                        binding.popUp.postDelayed({binding.textPopUp.text = ""}, 2005)
                    }else {

                        try {
                            inquilino = Inquilino(cpf, nome, caucao.toFloat())
                            inquilino!!.id = id
                            daoInqui.atualizarInquilino(inquilino!!)
                            fecharPopUp()
                            toggleItens()

                            lista = daoLocacao.retornarListaLocacao()
                            adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lista)
                            listView.adapter = adapter

                            binding.textPopUp.text = "Atualizado com sucesso!"
                            binding.popUp.visibility = View.VISIBLE
                            binding.popUp.postDelayed({binding.popUp.visibility = View.GONE}, 2000)
                            binding.popUp.postDelayed({binding.textPopUp.text = ""}, 2005)
                        }catch (e: Exception){
                            binding.textPopUp.text = "Erro ao atualizar.\nTente novamente."
                            binding.popUp.visibility = View.VISIBLE
                            binding.popUp.postDelayed({binding.popUp.visibility = View.GONE}, 2000)
                            binding.popUp.postDelayed({binding.textPopUp.text = ""}, 2005)
                        }

                    }
                }
                binding.btnCancelar.setOnClickListener(){
                    fecharTeclado()
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

    private fun fecharTeclado() {
        val view: View? = currentFocus
        if (view != null) {
            val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}