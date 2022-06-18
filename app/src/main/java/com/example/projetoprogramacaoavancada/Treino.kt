package com.example.projetoprogramacaoavancada

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Treino(

    var descricao: String,
    var idutilizador: Long,
    var id: Long = -1 ,

) {
    fun toContentValues(): ContentValues {
        val valores = ContentValues()

        valores.put(TabelaDBtreino.CAMPO_DESCRICAO, descricao)
        valores.put(TabelaDBtreino.CAMPO_UTILIZADOR_ID, idutilizador)

        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Treino {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posDesc = cursor.getColumnIndex(TabelaDBtreino.CAMPO_DESCRICAO)
            val posIdUti = cursor.getColumnIndex(TabelaDBtreino.CAMPO_UTILIZADOR_ID)

            val id = cursor.getLong(posId)
            val descricao = cursor.getString(posDesc)
            val idutilizador = cursor.getLong(posIdUti)

            return Treino(descricao,idutilizador,id)
        }
    }
}
