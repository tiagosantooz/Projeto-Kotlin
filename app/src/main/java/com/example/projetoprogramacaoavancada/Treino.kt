package com.example.projetoprogramacaoavancada

import android.content.ContentValues

data class Treino(

    var descricao: String,
    var idutilizador: Long,
    var id: Long = -1 ,

) {
    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaDBtreino.CAMPO_DESCRICAO, descricao)
        valores.put(TabelaDBtreino.CAMPO_UTILIZADOR_ID, idutilizador)

        return valores
    }
}

