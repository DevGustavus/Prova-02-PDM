package com.example.prova02pdm.banco

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDataBaseHelper(context: Context) : SQLiteOpenHelper(context, "BancoImobiliario", null, 1){

    override fun onCreate(db: SQLiteDatabase)
    {
        val nomeTabelaImovel = "Imovel"
        val idImovel = "id"
        val matricula = "matricula"
        val endereco = "endereco"
        val valorAluguel = "valorAluguel"
        val SQL_criacao1 =
            "CREATE TABLE ${nomeTabelaImovel} (" +
                    "${idImovel} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${matricula} TEXT," +
                    "${endereco} TEXT," +
                    "${valorAluguel} REAL)"
        db.execSQL(SQL_criacao1)

        val nomeTabelaProprietario = "Proprietario"
        val idProprietario = "id"
        val CPF_prop = "CPF_prop"
        val nomeProprietario = "nome"
        val email = "email"
        val SQL_criacao2 =
            "CREATE TABLE ${nomeTabelaProprietario} (" +
                    "${idProprietario} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${CPF_prop} TEXT," +
                    "${nomeProprietario} TEXT," +
                    "${email} TEXT)"
        db.execSQL(SQL_criacao2)

        val nomeTabelaInquilino = "Inquilino"
        val idInquilino = "id"
        val CPF_inq = "CPF_inq"
        val nomeInquilino = "nome"
        val Valor_depositado = "Valor_depositado"
        val SQL_criacao3 =
            "CREATE TABLE ${nomeTabelaInquilino} (" +
                    "${idInquilino} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${CPF_inq} TEXT," +
                    "${nomeInquilino} TEXT," +
                    "${Valor_depositado} REAL)"
        db.execSQL(SQL_criacao3)

        val nomeTabelaLocacao = "Locacao"
        val idLocacao = "id"
        val proprietario = "id_proprietario"
        val imovel = "id_imovel"
        val inquilino = "id_inquilino"
        val SQL_criacao4 =
            "CREATE TABLE ${nomeTabelaLocacao} (" +
                    "${idLocacao} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${proprietario} INTEGER," +
                    "${imovel} INTEGER," +
                    "${inquilino} INTEGER," +
                    "FOREIGN KEY(${proprietario}) REFERENCES Proprietario(${idProprietario})," +
                    "FOREIGN KEY(${imovel}) REFERENCES Imovel(${idImovel})," +
                    "FOREIGN KEY(${inquilino}) REFERENCES Inquilino(${idInquilino}))"
        db.execSQL(SQL_criacao4)
    }
    override fun onUpgrade(db: SQLiteDatabase, versaoAntiga: Int, novaVersao: Int) {
        val nomeTabelaImovel = "Imovel"
        val nomeTabelaProprietario = "Proprietario"
        val nomeTabelaInquilino = "Inquilino"
        val nomeTabelaLocacao = "Locacao"

        // Drop de todas as tabelas se elas existirem
        db.execSQL("DROP TABLE IF EXISTS $nomeTabelaImovel")
        db.execSQL("DROP TABLE IF EXISTS $nomeTabelaProprietario")
        db.execSQL("DROP TABLE IF EXISTS $nomeTabelaInquilino")
        db.execSQL("DROP TABLE IF EXISTS $nomeTabelaLocacao")

        // Criação de todas as tabelas novamente
        onCreate(db)
    }
}
