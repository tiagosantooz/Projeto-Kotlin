package com.example.projetoprogramacaoavancada

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns


data class Alimento(
    var nome : String,
    var quantidade: Int,
    var calorias: Int,
    var id: Long = -1
)
{
    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaDBalimento.CAMPO_NOME_ALIMENTO, nome)
        valores.put(TabelaDBalimento.CAMPO_QUANTIDADE_ALIMENTO, quantidade)
        valores.put(TabelaDBalimento.CAMPO_CALORIA_ALIMENTO, calorias)
        return valores
    }

    companion object{
        fun fromCursor(cursor: Cursor): Alimento{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaDBalimento.CAMPO_NOME_ALIMENTO)
            val posQuant = cursor.getColumnIndex(TabelaDBalimento.CAMPO_QUANTIDADE_ALIMENTO)
            val posCal = cursor.getColumnIndex(TabelaDBalimento.CAMPO_CALORIA_ALIMENTO)

            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNome)
            val quantidade = cursor.getInt(posQuant)
            val calorias = cursor.getInt(posCal)

            return Alimento(nome, quantidade,calorias,id)

        }
    }
}
