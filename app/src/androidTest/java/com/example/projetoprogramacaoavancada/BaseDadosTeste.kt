package com.example.projetoprogramacaoavancada

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BaseDadosTeste {
    private fun appContext() = InstrumentationRegistry.getInstrumentation().targetContext

    private fun getWritableDatabase(): SQLiteDatabase {
        val openHelper = GymDbOpenHelper(appContext())
        return openHelper.writableDatabase
    }

    private fun insereAlimento(db: SQLiteDatabase, alimento: Alimento) {
        alimento.id = TabelaDBalimento(db).insert(alimento.toContentValues())
        assertNotEquals(-1, alimento.id)
    }

    private fun insereDieta(db: SQLiteDatabase, dieta: Dieta) {
        dieta.id = TabelaDBdieta(db).insert(dieta.toContentValues())
        assertNotEquals(-1, dieta.id)
    }
/*
    private fun insereExercicio(db: SQLiteDatabase, exercicio: Exercicio) {
        exercicio.id = TabelaDBexercicio(db).insert(exercicio.toContentValues())
        assertNotEquals(-1, exercicio.id)
    }

    private fun insereTreino(db: SQLiteDatabase, treino: Treino) {
        treino.id = TabelaDBalimento(db).insert(treino.toContentValues())
        assertNotEquals(-1, treino.id)
    }

    private fun insereUtilizador(db: SQLiteDatabase, utilizador: Utilizador) {
        utilizador.id = TabelaDBdieta(db).insert(utilizador.toContentValues())
        assertNotEquals(-1, utilizador.id)
    }
*/


    @Before
    fun apagaBaseDados() {
        appContext().deleteDatabase(GymDbOpenHelper.NOME)
    }

    @Test
    fun consegueAbrirBaseDados() {
        val openHelper = GymDbOpenHelper(appContext())
        val db = openHelper.readableDatabase

        assertTrue(db.isOpen)

        db.close()
    }

    @Test
    fun consegueinserirDieta(){
        val db = getWritableDatabase()

        val dieta = Dieta("sugar-free","Dieta sem açucar",1)

        insereDieta(db, dieta)
    }
    @Test
    fun consegueinserirAlimento(){
        val db = getWritableDatabase()

        val dieta = Dieta("sugar-free","Sem açucar",1)
        insereDieta(db, dieta)

        val alimento = Alimento("couve",1,1,1)
        insereAlimento(db, alimento)

        db.close()

    }


    @Test
    fun consegueAlterarDieta(){
        val db = getWritableDatabase()

        val dieta = Dieta("sugar-free","Dieta sem açucar",1)
        insereDieta(db, dieta)

        dieta.nome = "semcalorias"

        val registosAlterados = TabelaDBdieta(db).update(
            dieta.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf("${dieta.id}")
        )

        assertEquals(1,registosAlterados)


        db.close()
    }


    @Test
    fun consegueAlterarAlimento(){
        val db = getWritableDatabase()

        val dieta1 = Dieta("x1","x1",1)
        insereDieta(db, dieta1)

        val dieta2 = Dieta("x2","x2",1)
        insereDieta(db, dieta2)

        val alimento = Alimento("Couve",1,1,dieta1.id)
        insereAlimento(db, alimento)

        alimento.nome = "couve"
        alimento.calorias = 7


        val registosAlterados = TabelaDBalimento(db).update(
            alimento.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf("${alimento.id}"))

        assertEquals(1,registosAlterados)
        db.close()

    }

    @Test
    fun consegueEliminarDieta(){

        val db = getWritableDatabase()

        val dieta = Dieta("teste","test",1)
        insereDieta(db ,dieta)

        val registosEliminados = TabelaDBdieta(db).delete(
            "${BaseColumns._ID}=?",
            arrayOf("${dieta.id}"))

        assertEquals(1,registosEliminados)
        db.close()

    }

    @Test
    fun consegueEliminarAlimento(){
        val db = getWritableDatabase()

        val dieta = Dieta("sugar-free","Dieta sem açucar",1)
        insereDieta(db,dieta)

        val alimento = Alimento("couve",2,2,dieta.id)
        insereAlimento(db,alimento)

        val registoEliminados = TabelaDBalimento(db).delete(
            "${BaseColumns._ID}=?",
            arrayOf("${alimento.id}"))

        assertEquals(1,registoEliminados)

        db.close()
    }

    @Test
    fun consegueLerdieta(){
        val db = getWritableDatabase()

        val dieta = Dieta("SugarFree","Dieta sem Açúcar",1)
        insereDieta(db, dieta)

        val cursor = TabelaDBdieta(db).query(
            TabelaDBdieta.TODAS_COLUNAS,
            "${BaseColumns._ID}=?",
            arrayOf("${dieta.id}"),
            null,
            null,
            null
        )

        assertEquals(1,cursor.count)
        assertTrue(cursor.moveToNext())

        val dietaDB = Dieta.fromCursor(cursor)

        assertEquals(dieta, dietaDB)

        db.close()

    }
}
