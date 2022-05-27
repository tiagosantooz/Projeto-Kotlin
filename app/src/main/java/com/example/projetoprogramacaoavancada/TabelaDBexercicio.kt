package com.example.projetoprogramacaoavancada

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaDBexercicio(val db: SQLiteDatabase) {
    fun cria() {
        db.execSQL("CREATE TABLE $NOME (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_NOME TEXT NOT NULL , $CAMPO_DESCRICAO TEXT NOT NULL , $CAMPO_MAQUINA TEXT NOT NULL , $CAMPO_CARGA INTEGER NOT NULL , $CAMPO_REPETICOES INTEGER NOT NULL)")
    }

    companion object {
        const val NOME = "pa_exercicio"
        const val CAMPO_NOME = "nome do exercicio"
        const val CAMPO_DESCRICAO = "descricao do exercicio"
        const val CAMPO_MAQUINA = "maquina do exercicio"
        const val CAMPO_CARGA = "carga do exercicio"
        const val CAMPO_REPETICOES = "repeticoes do exercicio"
    }
}
