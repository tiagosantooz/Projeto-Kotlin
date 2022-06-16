package com.example.projetoprogramacaoavancada

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaDBtreino(db: SQLiteDatabase) : TabelaDb(db,NOME){
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT , " +
                "$CAMPO_DESCRICAO TEXT NOT NULL , " +
                "$CAMPO_UTILIZADOR_ID INTEGER NOT NULL , " +
                "FOREIGN KEY ($CAMPO_UTILIZADOR_ID) REFERENCES ${TabelaDButilizador.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT)")
    }

    companion object {
        const val NOME = "pa_treino"
        const val CAMPO_DESCRICAO = "descricao"
        const val CAMPO_UTILIZADOR_ID = "utilizadorid"
    }
}