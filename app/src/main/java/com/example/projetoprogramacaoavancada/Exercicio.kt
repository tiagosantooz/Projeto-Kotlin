package com.example.projetoprogramacaoavancada

import android.content.ContentValues

data class Exercicio(
    var id: Long,
    var nome : String,
    var descricao: String,
    var maquina: Long,
    var carga : Long,
    var repeticoes : Long
) {
    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaDBexercicio.CAMPO_NOME, nome)
        valores.put(TabelaDBexercicio.CAMPO_DESCRICAO, descricao)
        valores.put(TabelaDBexercicio.CAMPO_MAQUINA, maquina)
        valores.put(TabelaDBexercicio.CAMPO_CARGA, carga)
        valores.put(TabelaDBexercicio.CAMPO_REPETICOES, repeticoes)

        return valores
    }
}

