package com.example.projetoprogramacaoavancada

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

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

    companion object{
        fun fromCursor(cursor: Cursor): TreinoExercicio{

            val posIdEx = cursor.getColumnIndex(TabelaDBtreinoExercicio.CAMPO_ID_EXERCICIO)
            val posIdTrei = cursor.getColumnIndex(TabelaDBtreinoExercicio.CAMPO_ID_TREINO)

            val idexercicio = cursor.getLong(posIdEx)
            val idtreino = cursor.getLong(posIdTrei)

            return TreinoExercicio(idexercicio,idtreino)

        }
    }
}

