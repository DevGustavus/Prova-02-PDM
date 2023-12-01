package com.example.prova02pdm.telas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

class TxtActivity : AppCompatActivity() {

    var banco : MyDataBaseHelper

    init {
        this.banco = banco
    }

    private lateinit var binding: ActivityTxtBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTxtBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = getColor(R.color.black)

        //CRIAR TXT
        binding.btn1.setOnClickListener(){

        }
        //MOSTRAR TXT
        binding.btn2.setOnClickListener(){

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

        // Caminho do arquivo
        val caminhoArquivo = "imoveis.txt"

        var listImoveis : List<Imovel> = daoImovel.mostrarImovel()
        var listProprietario : List<Proprietario> = daoProp.mostrarProprietario()
        var listInquilino : List<Inquilino> = daoInqui.mostrarInquilino()
        var listLocacoes : List<Locacao> = daoLocacao.mostrarLocacao()

        val conteudo = "Olá, este é um exemplo de conteúdo para o arquivo de texto!"

        // Tenta criar o arquivo e escrever o conteúdo
        try {
            // Criação do objeto File
            val arquivo = File(caminhoArquivo)

            // Criação do objeto BufferedWriter
            val escritor = BufferedWriter(FileWriter(arquivo))

            // Escrever o conteúdo no arquivo
            escritor.write("===========================================")
            for (imovel in listImoveis) {
                escritor.write("${imovel.toString()}\n")
            }
            escritor.write("\n")
            for (proprietario in listProprietario) {
                escritor.write("${proprietario.toString()}\n")
            }
            escritor.write("\n")
            for (inquilino in listInquilino) {
                escritor.write("${inquilino.toString()}\n")
            }
            escritor.write("\n")
            for (locacao in listLocacoes) {
                escritor.write("${locacao.toString()}\n")
            }
            escritor.write("===========================================")

            // Fechar o BufferedWriter para liberar os recursos
            escritor.close()

            android.util.Log.i("Teste","Arquivo criado com sucesso!")

        } catch (e: Exception) {
            android.util.Log.i("Erro","Ocorreu um erro ao criar o arquivo: ${e.message}")
        }
    }
}