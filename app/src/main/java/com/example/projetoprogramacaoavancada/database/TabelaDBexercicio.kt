package com.example.projetoprogramacaoavancada.database

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.provider.BaseColumns

class TabelaDBexercicio(db: SQLiteDatabase) : TabelaDb(db, NOME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT , " +
                "$CAMPO_NOME TEXT NOT NULL , " +
                "$CAMPO_DESCRICAO TEXT NOT NULL , " +
                "$CAMPO_CARGA INTEGER NOT NULL , " +
                "$CAMPO_REPETICOES INTEGER NOT NULL , " +
                "$CAMPO_MAQUINA_ID INTEGER NOT NULL , " +
                "FOREIGN KEY ($CAMPO_MAQUINA_ID) REFERENCES ${TabelaDBmaquina.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT)")
    }

    override fun query(
        columns: Array<String>,
        selection: String?,
        selectionArgs: Array<String>?,
        groupBy: String?,
        having: String?,
        orderBy: String?
    ): Cursor {
        val queryBuilder = SQLiteQueryBuilder()
        queryBuilder.tables = "$NOME INNER JOIN ${TabelaDBmaquina.NOME} ON ${TabelaDBmaquina.CAMPO_ID} = $CAMPO_MAQUINA_ID"

        return queryBuilder.query(db, columns, selection, selectionArgs, groupBy, having, orderBy)
    }

    companion object {
        const val NOME = "pa_exercicio"
        const val CAMPO_ID = "$NOME.${BaseColumns._ID}"
        const val CAMPO_NOME = "nome"
        const val CAMPO_DESCRICAO = "descricao"
        const val CAMPO_MAQUINA_ID = "maquinaId"
        const val CAMPO_CARGA = "carga"
        const val CAMPO_REPETICOES = "repeticoes"

        val TODAS_COLUNAS = arrayOf(CAMPO_ID, CAMPO_NOME, CAMPO_DESCRICAO, CAMPO_MAQUINA_ID, CAMPO_CARGA, CAMPO_REPETICOES, TabelaDBmaquina.CAMPO_NOME)
    }
}


