package com.example.projetoprogramacaoavancada.database

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaDBexercicio(db: SQLiteDatabase) : TabelaDb(db, NOME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT , " +
                "$CAMPO_NOME TEXT NOT NULL , " +
                "$CAMPO_DESCRICAO TEXT NOT NULL , " +
                "$CAMPO_MAQUINA TEXT NOT NULL , " +
                "$CAMPO_CARGA INTEGER NOT NULL , " +
                "$CAMPO_REPETICOES INTEGER NOT NULL)")
    }

    companion object {
        const val NOME = "pa_exercicio"
        const val CAMPO_NOME = "nome"
        const val CAMPO_DESCRICAO = "descricao"
        const val CAMPO_MAQUINA = "maquina"
        const val CAMPO_CARGA = "carga"
        const val CAMPO_REPETICOES = "repeticoes"

        val TODAS_COLUNAS = arrayOf(BaseColumns._ID, CAMPO_NOME, CAMPO_DESCRICAO, CAMPO_MAQUINA, CAMPO_CARGA, CAMPO_REPETICOES)
    }
}


