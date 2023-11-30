package com.example.prova02pdm.DAO

import android.content.ContentValues
import android.util.Log
import com.example.prova02pdm.banco.MyDataBaseHelper
import com.example.prova02pdm.classes.Imovel
import com.example.prova02pdm.classes.Proprietario

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

    fun excluirImovel(imovel : Imovel){
        val db_exclusao = this.banco.readableDatabase
        val condicao = "id = "+imovel.id
        val confirmaExclusao = db_exclusao.delete("Imovel", condicao, null)
        Log.i("Teste","Após a exclusão: "+confirmaExclusao)
    }

    fun atualizarImovel(imovel : Imovel): Boolean{
        val db_atualizacao = this.banco.writableDatabase
        val valores = ContentValues().apply{
            put("matricula", imovel.matricula)
            put("endereco", imovel.endereco)
            put("valorAluguel", imovel.valorAluguel)
        }
        val condicao = "id = "+imovel.id
        val confirmaAtualizacao = db_atualizacao.update("Imovel", valores, condicao, null)
        Log.i("Teste", "Atualização: $confirmaAtualizacao")
        if (confirmaAtualizacao <= 0){
            return false
        }
        return true
    }

    fun retornarImovel(id : Int): Imovel? {
        var imovel: Imovel? = null
        val db_leitura = this.banco.readableDatabase
        val cursor = db_leitura.rawQuery("select * from Imovel",null)
        with(cursor) {
            while (moveToNext()) {
                val id_BC = getInt(getColumnIndexOrThrow("id"))
                val matricula = getString(getColumnIndexOrThrow("matricula"))
                val endereco = getString(getColumnIndexOrThrow("endereco"))
                val valorAluguel = getFloat(getColumnIndexOrThrow("valorAluguel"))
                android.util.Log.i("Teste","ID: "+id_BC+" - Matricula: "+matricula+ " - Endereco: "+endereco+ " - ValorAluguel: "+valorAluguel)

                if(id == id_BC){
                    android.util.Log.i("Teste", "ID: "+id_BC+" - Matricula: "+matricula+ " - Endereco: "+endereco+ " - ValorAluguel: "+valorAluguel)
                    imovel = Imovel(matricula,endereco,valorAluguel)
                    break
                }
            }
        }
        cursor.close()
        return imovel
    }

    fun retornarUltimoID(): Int{
        val db_leitura = this.banco.readableDatabase
        val cursor = db_leitura.rawQuery("select * from Imovel",null)
        var ultimoId = 0
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow("id"))
                ultimoId = id
                android.util.Log.i("Teste","ID: "+id)
            }
        }
        cursor.close()
        return ultimoId
    }
}
