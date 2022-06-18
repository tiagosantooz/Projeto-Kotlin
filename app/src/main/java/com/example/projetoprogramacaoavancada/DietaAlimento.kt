package com.example.projetoprogramacaoavancada

import android.content.ContentValues

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
}

