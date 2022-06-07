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
    fun consegueinserirAlimento(){
        val db = getWritableDatabase()

        insereAlimento(db, Alimento(1,"Cenoura",5,100))
        db.close()
    }

    fun consegueinserirDieta(){
        val db = getWritableDatabase()

        val alimento = Alimento(1,"Couve",1,50)
        insereAlimento(db, alimento)

        val dieta = Dieta (1,"sugar-free","dieta sem açucar", alimento.id)
        insereDieta(db, dieta)

        db.close()

    }

}
