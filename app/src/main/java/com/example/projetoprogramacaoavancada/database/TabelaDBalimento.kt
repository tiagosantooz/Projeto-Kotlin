package com.example.projetoprogramacaoavancada.database

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaDBalimento (db : SQLiteDatabase) : TabelaDb(db, NOME) {

    override fun cria(){
        db.execSQL("CREATE TABLE $NOME (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT , " +
                "$CAMPO_NOME_ALIMENTO TEXT NOT NULL , " +
                "$CAMPO_QUANTIDADE_ALIMENTO INTEGER NOT NULL , " +
                "$CAMPO_CALORIA_ALIMENTO INTEGER NOT NULL)")
    }


    companion object {
        const val NOME = "pa_alimento"
        const val CAMPO_NOME_ALIMENTO = "nome"
        const val CAMPO_QUANTIDADE_ALIMENTO = "quantidade"
        const val CAMPO_CALORIA_ALIMENTO = "calorias"

        val TODAS_COLUNAS = arrayOf(BaseColumns._ID, CAMPO_NOME_ALIMENTO, CAMPO_QUANTIDADE_ALIMENTO, CAMPO_CALORIA_ALIMENTO)
    }
}

