package com.example.projetoprogramacaoavancada

import android.content.ContentValues

data class TreinoExercicio (
    var idexercicio : Long,
    var idtreino : Long
)
{
    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaDBtreinoExercicio.CAMPO_ID_EXERCICIO, idexercicio)
        valores.put(TabelaDBtreinoExercicio.CAMPO_ID_TREINO, idtreino)

        return valores
    }
}

