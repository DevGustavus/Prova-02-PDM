package com.example.prova02pdm.classes

class Locacao(proprietario : Proprietario, imovel : Imovel, inquilino : Inquilino){
    var proprietario : Proprietario
    var imovel : Imovel
    var inquilino : Inquilino
    var id : Int = 0

    init {
        this.proprietario = proprietario
        this.imovel = imovel
        this.inquilino = inquilino
    }

    override fun toString(): String {
        return "Locacao(proprietario=$proprietario, imovel=$imovel, inquilino=$inquilino)"
    }
}