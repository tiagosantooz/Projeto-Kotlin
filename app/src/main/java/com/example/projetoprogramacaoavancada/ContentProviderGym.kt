package com.example.projetoprogramacaoavancada

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

class ContentProviderGym : ContentProvider() {
    var db : GymDbOpenHelper? = null

    override fun onCreate(): Boolean {
        db = GymDbOpenHelper(context)

        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        TODO("Not yet implemented")
    }

    override fun getType(uri: Uri): String? =
        when(getUriMatcher().match(uri)){
            URI_UTILIZADORES -> "$MULTIPLOS_REGISTOS/${TabelaDButilizador.NOME}"
            URI_UTILIZADOR_ESPECIFICO -> "$UNICO_REGISTO/${TabelaDButilizador.NOME}"
            URI_DIETAS -> "$MULTIPLOS_REGISTOS/${TabelaDBdieta.NOME}"
            URI_DIETA_ESPECIFA -> "$UNICO_REGISTO/${TabelaDBdieta.NOME}"
            URI_TREINOS -> "$MULTIPLOS_REGISTOS/${TabelaDBtreino.NOME}"
            URI_TREINO_ESPECIFICO -> "$UNICO_REGISTO/${TabelaDBtreino.NOME}"
            URI_ALIMENTOS -> "$MULTIPLOS_REGISTOS/${TabelaDBalimento.NOME}"
            URI_ALIMENTO_ESPECIFICO -> "$UNICO_REGISTO/${TabelaDBalimento.NOME}"
            URI_EXERCICIOS -> "$MULTIPLOS_REGISTOS/${TabelaDBexercicio.NOME}"
            URI_EXERCICIOS_ESPECIFICO -> "$UNICO_REGISTO/${TabelaDBexercicio.NOME}"

            else -> null
        }


    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }

    companion object{
        const val AUTHORITY = "com.example.projetoprogramacaoavancada"

        const val URI_UTILIZADORES = 100
        const val URI_UTILIZADOR_ESPECIFICO = 101
        const val URI_DIETAS = 200
        const val URI_DIETA_ESPECIFA = 201
        const val URI_TREINOS = 300
        const val URI_TREINO_ESPECIFICO = 301
        const val URI_ALIMENTOS = 400
        const val URI_ALIMENTO_ESPECIFICO = 401
        const val URI_EXERCICIOS = 500
        const val URI_EXERCICIOS_ESPECIFICO = 501

        const val UNICO_REGISTO = "vnd.android.cursor.item"
        const val MULTIPLOS_REGISTOS = "vnd.android.cursor.dir"

        fun getUriMatcher() : UriMatcher {
            var uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

            uriMatcher.addURI(AUTHORITY, TabelaDButilizador.NOME, URI_UTILIZADORES)
            uriMatcher.addURI(AUTHORITY, "${TabelaDButilizador.NOME}/#", URI_UTILIZADOR_ESPECIFICO)
            uriMatcher.addURI(AUTHORITY,TabelaDBdieta.NOME, URI_DIETAS)
            uriMatcher.addURI(AUTHORITY,"${TabelaDBdieta.NOME}/#", URI_DIETA_ESPECIFA)
            uriMatcher.addURI(AUTHORITY,TabelaDBtreino.NOME, URI_TREINOS)
            uriMatcher.addURI(AUTHORITY,"${TabelaDBtreino.NOME}/#", URI_TREINO_ESPECIFICO)
            uriMatcher.addURI(AUTHORITY,TabelaDBalimento.NOME, URI_ALIMENTOS)
            uriMatcher.addURI(AUTHORITY,"${TabelaDBalimento.NOME}/#", URI_ALIMENTO_ESPECIFICO)
            uriMatcher.addURI(AUTHORITY,TabelaDBexercicio.NOME, URI_EXERCICIOS)
            uriMatcher.addURI(AUTHORITY,"${TabelaDBexercicio.NOME}/#", URI_EXERCICIOS_ESPECIFICO)

            return uriMatcher
        }
    }
}