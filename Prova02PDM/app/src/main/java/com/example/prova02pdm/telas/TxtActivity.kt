package com.example.prova02pdm.telas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
import com.example.prova02pdm.databinding.ActivityTxtBinding
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileReader
import java.io.FileWriter

class TxtActivity : AppCompatActivity() {

    var banco : MyDataBaseHelper

    init {
        banco = MyDataBaseHelper(this)
    }

    private lateinit var binding: ActivityTxtBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTxtBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = getColor(R.color.black)

        //CRIAR TXT
        binding.btn1.setOnClickListener(){
            escreverTxt()
        }
        //MOSTRAR TXT
        binding.btn2.setOnClickListener(){
            mostrarTxt()
        }
        binding.btn3.setOnClickListener(){
            mostrarTxtTela()
        }
        binding.btnBack.setOnClickListener(){
            finish()
        }
    }

    fun escreverTxt() {

        val daoImovel = ImovelDAO(this.banco)
        val daoProp = ProprietarioDAO(this.banco)
        val daoInqui = InquilinoDAO(this.banco)
        val daoLocacao = LocacaoDAO(this.banco)

        //Faz arquivo
        val arquivo = File(binding.root.context.filesDir, "imoveis.txt")

        var listImoveis : List<Imovel> = daoImovel.mostrarImovel()
        var listProprietario : List<Proprietario> = daoProp.mostrarProprietario()
        var listInquilino : List<Inquilino> = daoInqui.mostrarInquilino()
        var listLocacoes : List<Locacao> = daoLocacao.mostrarLocacao()

        // Tenta criar o arquivo e escrever o conteúdo
        try {
            //Cria arquivo
            arquivo.createNewFile()
            // Criação do objeto BufferedWriter
            val escritor = BufferedWriter(FileWriter(arquivo))

            // Escrever o conteúdo no arquivo
            escritor.write("======================================================")
            for (i in 0 until listLocacoes.size) {
                escritor.write("\nLocação - Id : ${listLocacoes[i].id}\n")
                escritor.write("${listImoveis[i]}\n")
                escritor.write("${listProprietario[i]}\n")
                escritor.write("${listInquilino[i]}\n")
                escritor.write("======================================================")
            }

            // Fechar o BufferedWriter para liberar os recursos
            escritor.close()

            android.util.Log.i("Teste","Arquivo criado com sucesso!")
            binding.textPopUp.text = "Arquivo criado com sucesso!"
            binding.popUp.visibility = View.VISIBLE
            binding.popUp.postDelayed({binding.popUp.visibility = View.GONE}, 2000)
            binding.popUp.postDelayed({binding.textPopUp.text = ""}, 2005)

        } catch (e: Exception) {
            android.util.Log.i("Erro","Ocorreu um erro ao criar o arquivo: ${e.message}")
            binding.textPopUp.text = "Erro ao criar o arquivo."
            binding.popUp.visibility = View.VISIBLE
            binding.popUp.postDelayed({binding.popUp.visibility = View.GONE}, 2000)
            binding.popUp.postDelayed({binding.textPopUp.text = ""}, 2005)
        }
    }

    private fun mostrarTxt() {
        try {
            val arquivo = File(binding.root.context.filesDir, "imoveis.txt")

            val reader = arquivo.bufferedReader()
            val leitura = reader.readLines()
            var dados = ""

            for (line in leitura) {
                //android.util.Log.i("BC Dados",line)
                dados += line + "\n"
            }
            android.util.Log.i("BC Dados",dados)

        } catch (e: Exception) {
            android.util.Log.i("Erro", "Ocorreu um erro ao ler o arquivo: ${e.message}")
            binding.textPopUp.text = "Erro ao ler o arquivo."
            binding.popUp.visibility = View.VISIBLE
            binding.popUp.postDelayed({binding.popUp.visibility = View.GONE}, 2000)
            binding.popUp.postDelayed({binding.textPopUp.text = ""}, 2005)
        }
    }

    private fun mostrarTxtTela() {
        try {
            val arquivo = File(binding.root.context.filesDir, "imoveis.txt")

            val reader = arquivo.bufferedReader()
            val leitura = reader.readLines()
            var dados = ""

            for (line in leitura) {
                //android.util.Log.i("BC Dados",line)
                dados += line + "\n"
            }
            binding.textPopUp.visibility = View.GONE
            binding.scrolldados.visibility = View.VISIBLE
            binding.textPopUpdados.text = dados
            binding.popUp.visibility = View.VISIBLE
            binding.btnBackPopUp.visibility = View.VISIBLE
            binding.btnBackPopUp.setOnClickListener(){
                binding.scrolldados.visibility = View.GONE
                binding.textPopUp.visibility = View.VISIBLE
                binding.textPopUp.text = ""
                binding.popUp.visibility = View.GONE
                binding.btnBackPopUp.visibility = View.GONE
            }

        } catch (e: Exception) {
            android.util.Log.i("Erro", "Ocorreu um erro ao ler o arquivo: ${e.message}")
            binding.textPopUp.text = "Erro ao ler o arquivo."
            binding.popUp.visibility = View.VISIBLE
            binding.popUp.postDelayed({binding.popUp.visibility = View.GONE}, 2000)
            binding.popUp.postDelayed({binding.textPopUp.text = ""}, 2005)
        }
    }
}