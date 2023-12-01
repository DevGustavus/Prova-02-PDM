package com.example.prova02pdm.classes

class Inquilino(CPF_inq : String, nome : String, Valor_depositado : Float){

    var id : Int = 0
    var CPF_inq : String
    var nome : String
    var Valor_depositado : Float

    init {
        this.CPF_inq = CPF_inq
        this.nome = nome
        this.Valor_depositado = Valor_depositado
    }

    override fun toString(): String {
        return "    Inquilino\n        CPF : $CPF_inq\n        Nome : $nome\n        Caução : R$$Valor_depositado"
    }
}