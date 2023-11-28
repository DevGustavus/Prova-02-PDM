package com.example.prova02pdm.DAO

import android.content.ContentValues
import android.util.Log
import com.example.prova02pdm.banco.MyDataBaseHelper
import com.example.prova02pdm.classes.Inquilino
import com.example.prova02pdm.classes.Locacao

class LocacaoDAO(banco : MyDataBaseHelper) {

    var banco : MyDataBaseHelper

    init {
        this.banco = banco
    }

    fun inserirLocacao(): Boolean{

        val daoImovel = ImovelDAO(this.banco)
        val daoProp = ProprietarioDAO(this.banco)
        val daoInqui = InquilinoDAO(this.banco)

        val db_insercao = this.banco.writableDatabase
        val valores = ContentValues().apply{
            put("id_proprietario", daoProp.retornarUltimoID())
            put("id_imovel", daoImovel.retornarUltimoID())
            put("id_inquilino", daoInqui.retornarUltimoID())
        }
        val confirmaInsercao = db_insercao?.insert("Locacao",  null, valores)
        Log.i("Teste","Inserção: "+confirmaInsercao)
        if (confirmaInsercao?.toInt()!! <= 0){
            return false
        }
        return true
    }

}
