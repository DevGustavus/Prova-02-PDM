package com.example.prova02pdm.DAO

import android.content.ContentValues
import android.util.Log
import com.example.prova02pdm.banco.MyDataBaseHelper
import com.example.prova02pdm.classes.Imovel

class ImovelDAO(banco : MyDataBaseHelper) {

    var banco : MyDataBaseHelper

    init {
        this.banco = banco
    }

    fun inserirImovel(imovel : Imovel): Boolean{
        val db_insercao = this.banco.writableDatabase
        val valores = ContentValues().apply{
            put("matricula", imovel.matricula)
            put("endereco", imovel.endereco)
            put("valorAluguel", imovel.valorAluguel)
        }
        val confirmaInsercao = db_insercao?.insert("Imovel",  null, valores)
        Log.i("Teste","Inserção: "+confirmaInsercao)
        if (confirmaInsercao?.toInt()!! <= 0){
            return false
        }
        return true
    }
}
