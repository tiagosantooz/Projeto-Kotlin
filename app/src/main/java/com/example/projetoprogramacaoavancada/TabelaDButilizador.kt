package com.example.projetoprogramacaoavancada

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaDButilizador(db: SQLiteDatabase) : TabelaDb(db,NOME){
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$CAMPO_NOME TEXT NOT NULL, " +
                "$CAMPO_SEXO TEXT NOT NULL," +
                "$CAMPO_IDADE INTEGER NOT NULL," +
                "$CAMPO_PESO INTEGER NOT NULL," +
                "$CAMPO_ALTURA INTEGER NOT NULL," +
                "FOREIGN KEY ($CAMPO_TREINO_ID) REFERENCES ${TabelaDBtreino.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT," +
                "FOREIGN KEY ($CAMPO_DIETA_ID) REFERENCES ${TabelaDBdieta.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT)")
    }

    companion object {
        const val NOME = "utilizador"
        const val CAMPO_NOME = "nome"
        const val CAMPO_SEXO = "sexo"
        const val CAMPO_IDADE = "idade"
        const val CAMPO_PESO = "peso"
        const val CAMPO_ALTURA = "altura"
        const val CAMPO_TREINO_ID = "treinoId"
        const val CAMPO_DIETA_ID = "dietaId"
    }
}

