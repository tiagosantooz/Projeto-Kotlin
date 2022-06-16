package com.example.projetoprogramacaoavancada

import android.content.ContentValues

data class Dieta(
    var id: Long,
    var nome : String,
    var descricao: String,
    var idutilizador: Long = -1
) {
    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaDBdieta.CAMPO_NOME, nome)
        valores.put(TabelaDBdieta.CAMPO_DESCRICAO, descricao)
        valores.put(TabelaDBdieta.CAMPO_UTILIZADOR_ID, idutilizador)

        return valores
    }
}