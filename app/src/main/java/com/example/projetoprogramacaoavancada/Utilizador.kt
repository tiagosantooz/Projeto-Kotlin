package com.example.projetoprogramacaoavancada

import android.content.ContentValues

data class Utilizador(
    var id: Long,
    var nome : String,
    var sexo: String,
    var idade: Long,
    var peso : Long,
    var altura : Long
) {
    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(Tabela_UTILIZADOR.CAMPO_NOME, nome)
        valores.put(Tabela_UTILIZADOR.CAMPO_IDADE, idade)
        valores.put(Tabela_UTILIZADOR.CAMPO_SEXO, sexo)
        valores.put(Tabela_UTILIZADOR.CAMPO_PESO, peso)
        valores.put(Tabela_UTILIZADOR.CAMPO_ALTURA, altura)

        return valores
    }
}