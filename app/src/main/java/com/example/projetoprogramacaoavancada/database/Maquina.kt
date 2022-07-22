package com.example.projetoprogramacaoavancada.database

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.io.Serializable

data class Maquina(var nome : String ="", var id: Long = -1) : Serializable {
    fun toContentValues() : ContentValues{
        val valores = ContentValues()
        valores.put(TabelaDBmaquina.CAMPO_NOME, nome)

        return valores
    }

    companion object{
        fun fromCursor(cursor: Cursor): Maquina{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaDBmaquina.CAMPO_NOME)

            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNome)

            return Maquina(nome, id)

        }
    }
}