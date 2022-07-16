package com.example.projetoprogramacaoavancada.database

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaDBtreinoExercicio(db : SQLiteDatabase) : TabelaDb(db, NOME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME ($CAMPO_ID_EXERCICIO INTEGER NOT NULL , " +
                "$CAMPO_ID_TREINO INTEGER NOT NULL , " +
                "FOREIGN KEY ($CAMPO_ID_EXERCICIO) REFERENCES ${TabelaDBexercicio.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT , " +
                "FOREIGN KEY ($CAMPO_ID_TREINO) REFERENCES ${TabelaDBtreino.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT)")
    }

    companion object{
        const val NOME = "pa_treino_exercicio"
        const val CAMPO_ID_EXERCICIO = "exercicioid"
        const val CAMPO_ID_TREINO  = "treinoid"

        val TODAS_COLUNAS = arrayOf(CAMPO_ID_EXERCICIO, CAMPO_ID_TREINO)
    }
}

