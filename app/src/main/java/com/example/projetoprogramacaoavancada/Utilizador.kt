package com.example.projetoprogramacaoavancada

import android.content.ContentValues

data class Utilizador(
    var id: Long = -1,
    var nome : String,
    var sexo: String,
    var idade: Long,
    var peso : Long,
    var altura : Long,

) {
    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaDButilizador.CAMPO_NOME, nome)
        valores.put(TabelaDButilizador.CAMPO_IDADE, idade)
        valores.put(TabelaDButilizador.CAMPO_SEXO, sexo)
        valores.put(TabelaDButilizador.CAMPO_PESO, peso)
        valores.put(TabelaDButilizador.CAMPO_ALTURA, altura)

        return valores
    }
}

