package com.example.projetoprogramacaoavancada.database

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaDBdieta(db: SQLiteDatabase) : TabelaDb(db, NOME){
     override fun cria() {
        db.execSQL("CREATE TABLE $NOME (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT , " +
                "$CAMPO_NOME TEXT NOT NULL , " +
                "$CAMPO_DESCRICAO TEXT NOT NULL , " +
                "$CAMPO_UTILIZADOR_ID INTEGER NOT NULL , " +
                "FOREIGN KEY ($CAMPO_UTILIZADOR_ID) REFERENCES ${TabelaDButilizador.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT)")
    }

    companion object {
        const val NOME = "pa_dieta"
        const val CAMPO_NOME = "nome"
        const val CAMPO_DESCRICAO = "descricao"
        const val CAMPO_UTILIZADOR_ID = "utilizadorid"

        val TODAS_COLUNAS = arrayOf(BaseColumns._ID, CAMPO_NOME, CAMPO_DESCRICAO, CAMPO_UTILIZADOR_ID)
    }
}

