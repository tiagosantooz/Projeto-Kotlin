package com.example.projetoprogramacaoavancada

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Dieta(

    var nome : String,
    var descricao: String,
    var idutilizador: Long,
    var id: Long = -1
) {
    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaDBdieta.CAMPO_NOME, nome)
        valores.put(TabelaDBdieta.CAMPO_DESCRICAO, descricao)
        valores.put(TabelaDBdieta.CAMPO_UTILIZADOR_ID, idutilizador)

        return valores
    }

    companion object{
        fun fromCursor(cursor: Cursor): Dieta{
         val posId = cursor.getColumnIndex(BaseColumns._ID)
         val posNome = cursor.getColumnIndex(TabelaDBdieta.CAMPO_NOME)
         val posDesc = cursor.getColumnIndex(TabelaDBdieta.CAMPO_DESCRICAO)
         val posIdUt = cursor.getColumnIndex(TabelaDBdieta.CAMPO_UTILIZADOR_ID)

         val id = cursor.getLong(posId)
         val nome = cursor.getString(posNome)
         val descricao = cursor.getString(posDesc)
         val idutilizador = cursor.getLong(posIdUt)

         return Dieta(nome, descricao, idutilizador, id)

        }
    }
}