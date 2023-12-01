package com.example.prova02pdm.DAO

import android.content.ContentValues
import android.util.Log
import com.example.prova02pdm.banco.MyDataBaseHelper
import com.example.prova02pdm.classes.Imovel
import com.example.prova02pdm.classes.Inquilino
import com.example.prova02pdm.classes.Proprietario

class InquilinoDAO(banco : MyDataBaseHelper) {

    var banco : MyDataBaseHelper

    init {
        this.banco = banco
    }

    fun inserirInquilino(inquilino : Inquilino): Boolean{
        val db_insercao = this.banco.writableDatabase
        val valores = ContentValues().apply{
            put("CPF_inq", inquilino.CPF_inq)
            put("nome", inquilino.nome)
            put("Valor_depositado", inquilino.Valor_depositado)
        }
        val confirmaInsercao = db_insercao?.insert("Inquilino",  null, valores)
        Log.i("Teste","Inserção: "+confirmaInsercao)
        if (confirmaInsercao?.toInt()!! <= 0){
            return false
        }
        return true
    }

    fun excluirInquilino(inquilino : Inquilino){
        val db_exclusao = this.banco.readableDatabase
        val condicao = "id = "+inquilino.id
        val confirmaExclusao = db_exclusao.delete("Inquilino", condicao, null)
        Log.i("Teste","Após a exclusão: "+confirmaExclusao)
    }

    fun atualizarInquilino(inquilino : Inquilino): Boolean{
        val db_atualizacao = this.banco.writableDatabase
        val valores = ContentValues().apply{
            put("CPF_inq", inquilino.CPF_inq)
            put("nome", inquilino.nome)
            put("Valor_depositado", inquilino.Valor_depositado)
        }
        val condicao = "id = "+inquilino.id
        val confirmaAtualizacao = db_atualizacao.update("Inquilino", valores, condicao, null)
        Log.i("Teste", "Atualização: $confirmaAtualizacao")
        if (confirmaAtualizacao <= 0){
            return false
        }
        return true
    }

    fun retornarInquilino(id : Int): Inquilino? {
        var inquilino: Inquilino? = null
        val db_leitura = this.banco.readableDatabase
        val cursor = db_leitura.rawQuery("select * from Inquilino",null)
        with(cursor) {
            while (moveToNext()) {
                val id_BC = getInt(getColumnIndexOrThrow("id"))
                val CPF_inq = getString(getColumnIndexOrThrow("CPF_inq"))
                val nome = getString(getColumnIndexOrThrow("nome"))
                val Valor_depositado = getFloat(getColumnIndexOrThrow("Valor_depositado"))
                android.util.Log.i("Teste","ID: "+id_BC+" - CPF_inq: "+CPF_inq+ " - Nome: "+nome+ " - Valor_depositado: "+Valor_depositado)

                if(id == id_BC){
                    android.util.Log.i("Teste","ID: "+id_BC+" - CPF_inq: "+CPF_inq+ " - Nome: "+nome+ " - Valor_depositado: "+Valor_depositado)
                    inquilino = Inquilino(CPF_inq,nome,Valor_depositado)
                    break
                }
            }
        }
        cursor.close()
        return inquilino
    }

    fun retornarUltimoID(): Int{
        val db_leitura = this.banco.readableDatabase
        val cursor = db_leitura.rawQuery("select * from Inquilino",null)
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

    fun mostrarInquilino(): List<Inquilino>{
        val listaInquilinos = ArrayList<Inquilino>()
        val db_leitura = this.banco.readableDatabase
        val cursor = db_leitura.rawQuery("select * from Inquilino",null)
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow("id"))
                val CPF_inq = getString(getColumnIndexOrThrow("CPF_inq"))
                val nome = getString(getColumnIndexOrThrow("nome"))
                val Valor_depositado = getFloat(getColumnIndexOrThrow("Valor_depositado"))
                android.util.Log.i("Teste","ID: "+id+" - CPF_inq: "+CPF_inq+ " - Nome: "+nome+ " - Valor_depositado: "+Valor_depositado)
                listaInquilinos.add(Inquilino(CPF_inq,nome,Valor_depositado))
            }
        }
        cursor.close()
        return(listaInquilinos)
    }
}
