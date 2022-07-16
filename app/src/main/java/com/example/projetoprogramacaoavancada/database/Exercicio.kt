package com.example.projetoprogramacaoavancada.database

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Exercicio(

    var nome : String,
    var descricao: String,
    var maquina: Long,
    var carga : Long,
    var repeticoes : Long,
    var id: Long = -1

)

{
    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaDBexercicio.CAMPO_NOME, nome)
        valores.put(TabelaDBexercicio.CAMPO_DESCRICAO, descricao)
        valores.put(TabelaDBexercicio.CAMPO_MAQUINA, maquina)
        valores.put(TabelaDBexercicio.CAMPO_CARGA, carga)
        valores.put(TabelaDBexercicio.CAMPO_REPETICOES, repeticoes)


        return valores
    }

    companion object{
        fun fromCursor(cursor: Cursor): Exercicio {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaDBexercicio.CAMPO_NOME)
            val posDesc = cursor.getColumnIndex(TabelaDBexercicio.CAMPO_DESCRICAO)
            val posMaq = cursor.getColumnIndex(TabelaDBexercicio.CAMPO_MAQUINA)
            val posCarga = cursor.getColumnIndex(TabelaDBexercicio.CAMPO_CARGA)
            val posRep = cursor.getColumnIndex(TabelaDBexercicio.CAMPO_REPETICOES)

            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNome)
            val descricao = cursor.getString(posDesc)
            val maquina = cursor.getLong(posMaq)
            val carga = cursor.getLong(posCarga)
            val repeticoes = cursor.getLong(posRep)

            return Exercicio(nome, descricao, maquina, carga, repeticoes, id)
        }
    }
}

