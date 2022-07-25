package com.example.projetoprogramacaoavancada.database

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.provider.BaseColumns

class TabelaDBtreino(db: SQLiteDatabase) : TabelaDb(db, NOME){
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT , " +
                "$CAMPO_DESCRICAO TEXT NOT NULL , " +
                "$CAMPO_UTILIZADOR_ID INTEGER NOT NULL , " +
                "FOREIGN KEY ($CAMPO_UTILIZADOR_ID) REFERENCES ${TabelaDButilizador.NOME}(${TabelaDButilizador.CAMPO_ID}) ON DELETE RESTRICT)")
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
        queryBuilder.tables = "$NOME INNER JOIN ${TabelaDButilizador.NOME} ON ${TabelaDButilizador.CAMPO_ID} = $CAMPO_UTILIZADOR_ID"

        return queryBuilder.query(db, columns, selection, selectionArgs, groupBy, having, orderBy)
    }
    companion object {
        const val NOME = "pa_treino"
        const val CAMPO_ID = "${TabelaDBtreino.NOME}.${BaseColumns._ID}"
        const val CAMPO_DESCRICAO = "descricao"
        const val CAMPO_UTILIZADOR_ID = "utilizadorid"

        val TODAS_COLUNAS = arrayOf(CAMPO_ID, CAMPO_DESCRICAO, CAMPO_UTILIZADOR_ID, TabelaDButilizador.CAMPO_NOME, TabelaDButilizador.CAMPO_ALTURA,
        TabelaDButilizador.CAMPO_SEXO, TabelaDButilizador.CAMPO_IDADE, TabelaDButilizador.CAMPO_PESO)
    }
}