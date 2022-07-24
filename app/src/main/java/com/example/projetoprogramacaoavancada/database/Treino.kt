package com.example.projetoprogramacaoavancada.database

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.io.Serializable

data class Treino(

    var descricao: String,
    var utilizador: Utilizador,
    var id: Long = -1 ,

) : Serializable

{
    fun toContentValues(): ContentValues {
        val valores = ContentValues()

        valores.put(TabelaDBtreino.CAMPO_DESCRICAO, descricao)
        valores.put(TabelaDBtreino.CAMPO_UTILIZADOR_ID, utilizador.id)

        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Treino {
            val posId = cursor.getColumnIndex(TabelaDBtreino.CAMPO_ID)
            val posDesc = cursor.getColumnIndex(TabelaDBtreino.CAMPO_DESCRICAO)
            val posIdUti = cursor.getColumnIndex(TabelaDBtreino.CAMPO_UTILIZADOR_ID)

            val posNomeUti = cursor.getColumnIndex(TabelaDButilizador.CAMPO_NOME)
            val posSexoUtilizador = cursor.getColumnIndex(TabelaDButilizador.CAMPO_SEXO)
            val posIdadeUtilizador = cursor.getColumnIndex(TabelaDButilizador.CAMPO_IDADE)
            val posAlturaUtilizador = cursor.getColumnIndex(TabelaDButilizador.CAMPO_ALTURA)
            val posPesoUtilizador = cursor.getColumnIndex(TabelaDButilizador.CAMPO_PESO)


            val id = cursor.getLong(posId)
            val descricao = cursor.getString(posDesc)


            val idUtilizador = cursor.getLong(posIdUti)
            val nomeUtilizador = cursor.getString(posNomeUti)
            val sexoUtilizador = cursor.getString(posSexoUtilizador)
            val alturaUtilizador = cursor.getLong(posAlturaUtilizador)
            val pesoUtilizador = cursor.getLong(posPesoUtilizador)
            val idadeUtilizador = cursor.getLong(posIdadeUtilizador)

            val utilizador = Utilizador(nomeUtilizador, sexoUtilizador, idadeUtilizador, pesoUtilizador, alturaUtilizador,idUtilizador)

            return Treino(descricao,utilizador,id)
        }
    }
}
