package com.example.prova02pdm.classes

class Proprietario(CPF_prop : String, nome : String, email : String) {

    var id : Int= 0
    var CPF_prop : String
    var nome : String
    var email : String

    init {
        this.CPF_prop = CPF_prop
        this.nome = nome
        this.email = email
    }

    override fun toString(): String {
        return "    Proprietário\n        CPF : $CPF_prop\n        Nome : $nome\n        Email : $email"
    }
}