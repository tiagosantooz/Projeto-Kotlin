package com.example.projetoprogramacaoavancada

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaDBdietaAlimento(db : SQLiteDatabase) : TabelaDb(db, NOME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME ($CAMPO_ID_ALIMENTO INTEGER NOT NULL , " +
                "$CAMPO_ID_DIETA INTEGER NOT NULL , " +
                "FOREIGN KEY ($CAMPO_ID_ALIMENTO) REFERENCES ${TabelaDBdieta.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT , " +
                "FOREIGN KEY ($CAMPO_ID_DIETA) REFERENCES ${TabelaDBalimento.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT)")
    }

    companion object{
        const val NOME = "pa_dieta_alimento"
        const val CAMPO_ID_ALIMENTO = "alimentoid"
        const val CAMPO_ID_DIETA  = "dietaid"

        val TODAS_COLUNAS = arrayOf(CAMPO_ID_ALIMENTO, CAMPO_ID_DIETA)
    }
}

