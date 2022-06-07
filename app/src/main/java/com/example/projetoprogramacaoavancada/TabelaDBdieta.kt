package com.example.projetoprogramacaoavancada

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaDBdieta(db: SQLiteDatabase) : TabelaDb(db, NOME){
     override fun cria() {
        db.execSQL("CREATE TABLE $NOME (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT , $CAMPO_NOME TEXT NOT NULL , $CAMPO_DESCRICAO TEXT NOT NULL , FOREIGN KEY ($CAMPO_ALIMENTO_ID) REFERENCES ${TabelaDBalimento.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT)")
    }

    companion object {
        const val NOME = "pa_exercicio"
        const val CAMPO_NOME = "nome"
        const val CAMPO_DESCRICAO = "descricao"
        const val CAMPO_ALIMENTO_ID = "alimentoId"
    }
}

