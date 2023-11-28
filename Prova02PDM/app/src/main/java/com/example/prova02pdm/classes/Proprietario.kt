package com.example.prova02pdm.classes

class Proprietario(CPF_prop : String, nome : String, email : String) {

    var CPF_prop : String
    var nome : String
    var email : String

    init {
        this.CPF_prop = CPF_prop
        this.nome = nome
        this.email = email
    }

    override fun toString(): String {
        return "Proprietario(CPF_prop='$CPF_prop', nome='$nome', email='$email')"
    }
}