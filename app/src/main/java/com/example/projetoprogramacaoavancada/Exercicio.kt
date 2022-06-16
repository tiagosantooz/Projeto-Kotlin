package com.example.projetoprogramacaoavancada

import android.content.ContentValues

data class Exercicio(
    var id: Long,
    var nome : String,
    var descricao: String,
    var maquina: Long,
    var carga : Long,
    var repeticoes : Long,
    var idtreino : Long = -1

)

{
    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaDBexercicio.CAMPO_NOME, nome)
        valores.put(TabelaDBexercicio.CAMPO_DESCRICAO, descricao)
        valores.put(TabelaDBexercicio.CAMPO_MAQUINA, maquina)
        valores.put(TabelaDBexercicio.CAMPO_CARGA, carga)
        valores.put(TabelaDBexercicio.CAMPO_REPETICOES, repeticoes)
        valores.put(TabelaDBexercicio.CAMPO_TREINO_ID, idtreino)

        return valores
    }
}

