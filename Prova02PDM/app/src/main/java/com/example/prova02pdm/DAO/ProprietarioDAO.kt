package com.example.prova02pdm.DAO;

import android.content.ContentValues
import android.util.Log
import com.example.prova02pdm.banco.MyDataBaseHelper;
import com.example.prova02pdm.classes.Inquilino
import com.example.prova02pdm.classes.Proprietario

class ProprietarioDAO(banco : MyDataBaseHelper) {

    var banco : MyDataBaseHelper

    init {
        this.banco = banco
    }

    fun inserirProprietario(proprietario : Proprietario): Boolean{
        val db_insercao = this.banco.writableDatabase
        val valores = ContentValues().apply{
            put("CPF_prop", proprietario.CPF_prop)
            put("nome", proprietario.nome)
            put("email", proprietario.email)
        }
        val confirmaInsercao = db_insercao?.insert("Proprietario",  null, valores)
        Log.i("Teste","Inserção: "+confirmaInsercao)
        if (confirmaInsercao?.toInt()!! <= 0){
            return false
        }
        return true
    }

    fun excluirProprietario(proprietario : Proprietario){
        val db_exclusao = this.banco.readableDatabase
        val condicao = "id = "+proprietario.id
        val confirmaExclusao = db_exclusao.delete("Proprietario", condicao, null)
        Log.i("Teste","Após a exclusão: "+confirmaExclusao)
    }

    fun atualizarProprietario(proprietario : Proprietario): Boolean{
        val db_atualizacao = this.banco.writableDatabase
        val valores = ContentValues().apply{
            put("CPF_prop", proprietario.CPF_prop)
            put("nome", proprietario.nome)
            put("email", proprietario.email)
        }
        val condicao = "id = "+proprietario.id
        val confirmaAtualizacao = db_atualizacao.update("Proprietario", valores, condicao, null)
        Log.i("Teste", "Atualização: $confirmaAtualizacao")
        if (confirmaAtualizacao <= 0){
            return false
        }
        return true
    }

    fun retornarProprietario(id : Int): Proprietario? {
        var proprietario: Proprietario? = null
        val db_leitura = this.banco.readableDatabase
        val cursor = db_leitura.rawQuery("select * from Proprietario",null)
        with(cursor) {
            while (moveToNext()) {
                val id_BC = getInt(getColumnIndexOrThrow("id"))
                val CPF_prop = getString(getColumnIndexOrThrow("CPF_inq"))
                val nome = getString(getColumnIndexOrThrow("nome"))
                val email = getString(getColumnIndexOrThrow("email"))
                android.util.Log.i("Teste","ID: "+id_BC+" - CPF_prop: "+CPF_prop+ " - Nome: "+nome+ " - Email: "+email)

                if(id == id_BC){
                    android.util.Log.i("Teste","ID: "+id_BC+" - CPF_prop: "+CPF_prop+ " - Nome: "+nome+ " - Email: "+email)
                    proprietario = Proprietario(CPF_prop,nome,email)
                    break
                }
            }
        }
        cursor.close()
        return proprietario
    }

    fun retornarUltimoID(): Int{
        val db_leitura = this.banco.readableDatabase
        val cursor = db_leitura.rawQuery("select * from Proprietario",null)
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
