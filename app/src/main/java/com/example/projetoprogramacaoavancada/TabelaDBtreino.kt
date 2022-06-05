package com.example.projetoprogramacaoavancada

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaDBtreino(val db: SQLiteDatabase) {
    fun cria() {
        db.execSQL("CREATE TABLE $NOME (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT , $CAMPO_DESCRICAO TEXT NOT NULL , FOREIGN KEY ($CAMPO_EXERCICIO_ID) REFERENCES ${TabelaDBexercicio.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT)")
    }

    companion object {
        const val NOME = "treino"
        const val CAMPO_DESCRICAO = "descricao"
        const val CAMPO_EXERCICIO_ID = "alimentoId"
    }
}