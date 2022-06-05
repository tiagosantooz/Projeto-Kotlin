package com.example.projetoprogramacaoavancada

import android.content.ContentValues

data class Treino(
    var id: Long,
    var descricao: String,
    var idExercicio : Long
) {
    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaDBtreino.CAMPO_DESCRICAO, descricao)
        valores.put(TabelaDBtreino.CAMPO_EXERCICIO_ID, idExercicio)

        return valores
    }
}

