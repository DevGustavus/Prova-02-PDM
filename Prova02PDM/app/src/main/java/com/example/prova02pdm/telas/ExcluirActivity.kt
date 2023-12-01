package com.example.prova02pdm.telas

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import com.example.prova02pdm.databinding.ActivityExcluirBinding

class ExcluirActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExcluirBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExcluirBinding.inflate(layoutInflater)
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

        var id = 0

        var imovel: Imovel? = null
        var proprietario : Proprietario? = null
        var inquilino : Inquilino? = null
        var locacao : Locacao? = null

        binding.layoutBtn.setOnClickListener(){

            fecharTeclado()

            if (binding.editTextId.text.toString() != ""){
                id = binding.editTextId.text.toString().toInt()
                try {
                    locacao = daoLocacao.retornarLocacao(id)
                    inquilino = daoInqui.retornarInquilino(id)
                    imovel = daoImovel.retornarImovel(id)
                    proprietario = daoProp.retornarProprietario(id)

                    if (imovel != null && proprietario != null && inquilino != null && locacao != null){
                        locacao!!.id = id
                        inquilino!!.id = id
                        imovel!!.id = id
                        proprietario!!.id = id

                        daoLocacao.excluirLocacao(locacao!!)
                        daoInqui.excluirInquilino(inquilino!!)
                        daoImovel.excluirImovel(imovel!!)
                        daoProp.excluirProprietario(proprietario!!)

                        lista = daoLocacao.retornarListaLocacao()
                        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lista)
                        listView.adapter = adapter

                        binding.textPopUp.text = "Excluido com sucesso!"
                        binding.popUp.visibility = View.VISIBLE
                        binding.popUp.postDelayed({binding.popUp.visibility = View.GONE}, 2000)
                        binding.popUp.postDelayed({binding.textPopUp.text = ""}, 2005)
                    }else {
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
        }

        binding.btnBack.setOnClickListener(){
            finish()
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