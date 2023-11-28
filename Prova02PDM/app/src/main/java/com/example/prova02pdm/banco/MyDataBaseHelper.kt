package com.example.flowcopy.DAO

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDataBaseHelper(context: Context) : SQLiteOpenHelper(context, "BancoConta", null, 1){

    override fun onCreate(db: SQLiteDatabase)
    {
        val nomeTabela = "Conta"
        val id = "id"
        val nome = "nome"
        val email = "email"
        val senha = "senha"
        val logado = "logado"
        val SQL_criacao =
            "CREATE TABLE ${nomeTabela} (" +
                    "${id} INTEGER PRIMARY KEY," +
                    "${nome} TEXT," +
                    "${email} TEXT," +
                    "${senha} TEXT," +
                    "${logado} INTEGER)"
        db.execSQL(SQL_criacao)
    }
    override fun onUpgrade(db: SQLiteDatabase, versaoAntiga: Int, novaVersao: Int) {
        val SQL_exclusao = "DROP TABLE IF EXISTS Conta"
        db.execSQL(SQL_exclusao)
        onCreate(db)
    }

}
