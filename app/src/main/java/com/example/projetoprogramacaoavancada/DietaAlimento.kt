package com.example.projetoprogramacaoavancada

import android.content.ContentValues

data class DietaAlimento (
    var idalimento : Long = -1,
    var iddieta : Long = -1
)
{
    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaDBdietaAlimento.CAMPO_ID_ALIMENTO, idalimento)
        valores.put(TabelaDBdietaAlimento.CAMPO_ID_DIETA, iddieta)

        return valores
    }
}

