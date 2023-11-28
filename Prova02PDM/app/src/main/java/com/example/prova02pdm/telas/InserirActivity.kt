package com.example.prova02pdm.telas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.prova02pdm.DAO.ImovelDAO
import com.example.prova02pdm.DAO.InquilinoDAO
import com.example.prova02pdm.DAO.LocacaoDAO
import com.example.prova02pdm.DAO.ProprietarioDAO
import com.example.prova02pdm.R
import com.example.prova02pdm.banco.MyDataBaseHelper
import com.example.prova02pdm.classes.Imovel
import com.example.prova02pdm.classes.Inquilino
import com.example.prova02pdm.classes.Proprietario
import com.example.prova02pdm.databinding.ActivityInserirBinding

class InserirActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInserirBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInserirBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val daoImovel = ImovelDAO(MyDataBaseHelper(applicationContext))
        val daoProp = ProprietarioDAO(MyDataBaseHelper(applicationContext))
        val daoInqui = InquilinoDAO(MyDataBaseHelper(applicationContext))
        val daoLocacao = LocacaoDAO(MyDataBaseHelper(applicationContext))

        var imovel: Imovel? = null
        var proprietario : Proprietario? = null
        var inquilino : Inquilino? = null

        binding.layoutBtnLocacao.setOnClickListener(){
            binding.popUpSelect.visibility = View.VISIBLE

            binding.layoutBtnImovel.setOnClickListener(){

                binding.popUpSelect.isClickable = false
                binding.popUpSelect.visibility = View.INVISIBLE

                imovelPopUp()

                binding.btnOk.setOnClickListener(){
                    val matricula = binding.editTextFirst.text.toString()
                    val endereco = binding.editTextSecond.text.toString()
                    val aluguel = binding.editTextFloat.text.toString()

                    imovel = Imovel(matricula, endereco, aluguel.toFloat())

                    fecharPopUp()
                    binding.popUpSelect.isClickable = true
                    binding.popUpSelect.visibility = View.VISIBLE
                }
                binding.btnCancelar.setOnClickListener(){
                    fecharPopUp()
                    binding.popUpSelect.isClickable = true
                    binding.popUpSelect.visibility = View.VISIBLE
                }
            }
            binding.layoutBtnProprietario.setOnClickListener(){

                binding.popUpSelect.isClickable = false
                binding.popUpSelect.visibility = View.INVISIBLE

                proprietarioPopUp()

                binding.btnOk.setOnClickListener(){
                    val cpf = binding.editTextFirst.text.toString()
                    val nome = binding.editTextSecond.text.toString()
                    val email = binding.editTextEmail.text.toString()

                    proprietario = Proprietario(cpf, nome, email)

                    fecharPopUp()
                    binding.popUpSelect.isClickable = true
                    binding.popUpSelect.visibility = View.VISIBLE
                }
                binding.btnCancelar.setOnClickListener(){
                    fecharPopUp()
                    binding.popUpSelect.isClickable = true
                    binding.popUpSelect.visibility = View.VISIBLE
                }
            }
            binding.layoutBtnInquilino.setOnClickListener(){

                binding.popUpSelect.isClickable = false
                binding.popUpSelect.visibility = View.INVISIBLE

                inquilinoPopUp()

                binding.btnOk.setOnClickListener(){
                    val cpf = binding.editTextFirst.text.toString()
                    val nome = binding.editTextSecond.text.toString()
                    val caucao = binding.editTextFloat.text.toString()

                    inquilino = Inquilino(cpf, nome, caucao.toFloat())

                    fecharPopUp()
                    binding.popUpSelect.isClickable = true
                    binding.popUpSelect.visibility = View.VISIBLE
                }
                binding.btnCancelar.setOnClickListener(){
                    fecharPopUp()
                    binding.popUpSelect.isClickable = true
                    binding.popUpSelect.visibility = View.VISIBLE
                }
            }
            binding.btnInserir.setOnClickListener(){
                if (imovel == null){
                    binding.textPopUp.text = "Preencha o Imóvel"
                    binding.popUp.visibility = View.VISIBLE
                    binding.popUp.postDelayed({binding.popUp.visibility = View.INVISIBLE}, 2000)
                    binding.popUp.postDelayed({binding.textPopUp.text = ""}, 2005)
                }else if (proprietario == null){
                    binding.textPopUp.text = "Preencha o Proprietário"
                    binding.popUp.visibility = View.VISIBLE
                    binding.popUp.postDelayed({binding.popUp.visibility = View.INVISIBLE}, 2000)
                    binding.popUp.postDelayed({binding.textPopUp.text = ""}, 2005)
                }else if (inquilino == null){
                binding.textPopUp.text = "Preencha o Inquilino"
                binding.popUp.visibility = View.VISIBLE
                binding.popUp.postDelayed({binding.popUp.visibility = View.INVISIBLE}, 2000)
                binding.popUp.postDelayed({binding.textPopUp.text = ""}, 2005)
                }else {
                    if (daoImovel.inserirImovel(imovel!!) && daoProp.inserirProprietario(proprietario!!) && daoInqui.inserirInquilino(inquilino!!)) {
                        if (daoLocacao.inserirLocacao()){
                            binding.textPopUp.text = "Inserido com sucesso!!"
                            binding.popUp.visibility = View.VISIBLE
                            binding.popUp.postDelayed({binding.popUp.visibility = View.INVISIBLE}, 2000)
                            binding.popUp.postDelayed({binding.textPopUp.text = ""}, 2005)
                            binding.popUp.postDelayed({binding.popUpSelect.visibility = View.INVISIBLE}, 2350)
                        }else {
                            binding.textPopUp.text = "Erro ao inserir a locação."
                            binding.popUp.visibility = View.VISIBLE
                            binding.popUp.postDelayed({binding.popUp.visibility = View.INVISIBLE}, 2000)
                            binding.popUp.postDelayed({binding.textPopUp.text = ""}, 2005)
                        }
                    }else {
                        binding.textPopUp.text = "Erro na inserção dos dados."
                        binding.popUp.visibility = View.VISIBLE
                        binding.popUp.postDelayed({binding.popUp.visibility = View.INVISIBLE}, 2000)
                        binding.popUp.postDelayed({binding.textPopUp.text = ""}, 2005)
                    }
                }
            }
            binding.btnCancelarInsercao.setOnClickListener(){
                imovel = null
                proprietario = null
                inquilino = null
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

    }

    private fun proprietarioPopUp(){

        binding.popUpInput.visibility = View.VISIBLE

        binding.tituloPopUp.text = "Inserir Proprietário"
        binding.editTextFirst.hint = "CPF"
        binding.editTextSecond.hint = "Nome"
        binding.editTextFloat.visibility = View.INVISIBLE

    }

    private fun inquilinoPopUp(){

        binding.popUpInput.visibility = View.VISIBLE

        binding.tituloPopUp.text = "Inserir Inquilino"
        binding.editTextFirst.hint = "CPF"
        binding.editTextSecond.hint = "Nome"
        binding.editTextEmail.visibility = View.INVISIBLE
        binding.editTextFloat.hint = "Valor do Caução Depositado"

    }
}