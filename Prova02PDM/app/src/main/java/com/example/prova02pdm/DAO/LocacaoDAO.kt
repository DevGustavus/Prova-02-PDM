package com.example.prova02pdm.DAO

import android.content.ContentValues
import android.util.Log
import com.example.prova02pdm.banco.MyDataBaseHelper
import com.example.prova02pdm.classes.Inquilino
import com.example.prova02pdm.classes.Locacao
import com.example.prova02pdm.classes.Proprietario

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

    fun excluirLocacao(locacao : Locacao){
        val db_exclusao = this.banco.readableDatabase
        val condicao = "id = "+locacao.id
        val confirmaExclusao = db_exclusao.delete("Locacao", condicao, null)
        Log.i("Teste","Após a exclusão: "+confirmaExclusao)
    }

    fun retornarLocacao(id : Int): Locacao? {
        val daoImovel = ImovelDAO(this.banco)
        val daoProp = ProprietarioDAO(this.banco)
        val daoInqui = InquilinoDAO(this.banco)

        var locacao: Locacao? = null
        val db_leitura = this.banco.readableDatabase
        val cursor = db_leitura.rawQuery("select * from Locacao",null)
        with(cursor) {
            while (moveToNext()) {
                val id_BC = getInt(getColumnIndexOrThrow("id"))
                val id_proprietario = getInt(getColumnIndexOrThrow("id_proprietario"))
                val id_imovel = getInt(getColumnIndexOrThrow("id_imovel"))
                val id_inquilino = getInt(getColumnIndexOrThrow("id_inquilino"))
                android.util.Log.i("Teste","ID: "+id_BC+" - id_proprietario: "+id_proprietario+ " - id_imovel: "+id_imovel+ " - id_inquilino: "+id_inquilino)

                if(id == id_BC){
                    android.util.Log.i("Teste","ID: "+id_BC+" - id_proprietario: "+id_proprietario+ " - id_imovel: "+id_imovel+ " - id_inquilino: "+id_inquilino)
                    val objProprietario = daoProp.retornarProprietario(id_proprietario)
                    val objImovel = daoImovel.retornarImovel(id_imovel)
                    val objInquilino = daoInqui.retornarInquilino(id_inquilino)
                    locacao = Locacao(objProprietario!!,objImovel!!,objInquilino!!)
                    break
                }
            }
        }
        cursor.close()
        return locacao
    }

}
