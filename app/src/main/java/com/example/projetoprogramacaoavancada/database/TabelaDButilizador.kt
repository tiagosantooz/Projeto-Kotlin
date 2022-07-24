package com.example.projetoprogramacaoavancada.database

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaDButilizador(db: SQLiteDatabase) : TabelaDb(db, NOME){
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$CAMPO_NOME TEXT NOT NULL, " +
                "$CAMPO_SEXO TEXT NOT NULL," +
                "$CAMPO_IDADE INTEGER NOT NULL," +
                "$CAMPO_PESO INTEGER NOT NULL," +
                "$CAMPO_ALTURA INTEGER NOT NULL)")
    }

    companion object {
        const val NOME = "pa_utilizador"
        const val CAMPO_ID = "$NOME.${BaseColumns._ID}"
        const val CAMPO_NOME = "nome"
        const val CAMPO_SEXO = "sexo"
        const val CAMPO_IDADE = "idade"
        const val CAMPO_PESO = "peso"
        const val CAMPO_ALTURA = "altura"

        val TODAS_COLUNAS = arrayOf(BaseColumns._ID, CAMPO_NOME, CAMPO_SEXO, CAMPO_IDADE, CAMPO_PESO,
            CAMPO_ALTURA
        )
    }
}

