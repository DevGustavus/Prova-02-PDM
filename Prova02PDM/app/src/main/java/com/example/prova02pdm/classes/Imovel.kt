package com.example.prova02pdm.classes

class Imovel(matricula : String, endereco : String, valorAluguel : Float) {

    var id : Int = 0
    var matricula : String
    var endereco : String
    var valorAluguel : Float

    init {
        this.matricula = matricula
        this.endereco = endereco
        this.valorAluguel = valorAluguel
    }

    override fun toString(): String {
        return "    Imóvel\n        Matricula : $matricula\n        Endereco : $endereco\n        Aluguel : R$$valorAluguel"
    }
}