package com.example.projetoprogramacaoavancada

import android.content.ContentValues
import android.database.Cursor

data class DietaAlimento (
    var idalimento : Long,
    var iddieta : Long
)
{
    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaDBdietaAlimento.CAMPO_ID_ALIMENTO, idalimento)
        valores.put(TabelaDBdietaAlimento.CAMPO_ID_DIETA, iddieta)

        return valores
    }

    companion object{
        fun fromCursor(cursor: Cursor): DietaAlimento{
            val posIdAli = cursor.getColumnIndex(TabelaDBdietaAlimento.CAMPO_ID_ALIMENTO)
            val posIdDieta = cursor.getColumnIndex(TabelaDBdietaAlimento.CAMPO_ID_DIETA)

            val idalimento = cursor.getLong(posIdAli)
            val iddieta = cursor.getLong(posIdDieta)

            return DietaAlimento(idalimento,iddieta)

        }
    }
}

