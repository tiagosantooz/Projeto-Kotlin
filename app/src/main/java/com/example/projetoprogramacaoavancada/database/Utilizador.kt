package com.example.projetoprogramacaoavancada.database

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.io.Serializable

data class Utilizador(

    var nome: String = "",
    var sexo: String = "",
    var idade: Long = 0,
    var peso: Long = 0,
    var altura: Long = 0,
    var id: Long = -1
) : Serializable {
    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaDButilizador.CAMPO_NOME, nome)
        valores.put(TabelaDButilizador.CAMPO_IDADE, idade)
        valores.put(TabelaDButilizador.CAMPO_SEXO, sexo)
        valores.put(TabelaDButilizador.CAMPO_PESO, peso)
        valores.put(TabelaDButilizador.CAMPO_ALTURA, altura)

        return valores
    }

    companion object{
        fun fromCursor(cursor: Cursor): Utilizador {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaDButilizador.CAMPO_NOME)
            val posSex = cursor.getColumnIndex(TabelaDButilizador.CAMPO_SEXO)
            val posIdade = cursor.getColumnIndex(TabelaDButilizador.CAMPO_IDADE)
            val posPeso = cursor.getColumnIndex(TabelaDButilizador.CAMPO_PESO)
            val posAlt = cursor.getColumnIndex(TabelaDButilizador.CAMPO_ALTURA)

            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNome)
            val sexo = cursor.getString(posSex)
            val idade = cursor.getLong(posIdade)
            val peso = cursor.getLong(posPeso)
            val altura = cursor.getLong(posAlt)

            return Utilizador(nome, sexo, idade, peso, altura, id)

        }
    }
}

