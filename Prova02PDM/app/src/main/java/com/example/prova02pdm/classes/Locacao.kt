package com.example.prova02pdm.classes

class Locacao(proprietario : Proprietario, imovel : Imovel, locacao : Locacao, inquilino : Inquilino){
    var proprietario : Proprietario
    var imovel : Imovel
    var locacao : Locacao
    var inquilino : Inquilino

    init {
        this.proprietario = proprietario
        this.imovel = imovel
        this.locacao = locacao
        this.inquilino = inquilino
    }

    override fun toString(): String {
        return "Locacao(proprietario=$proprietario, locacao=$locacao, inquilino=$inquilino)"
    }
}