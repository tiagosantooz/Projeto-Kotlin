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

        val alimento = Alimento(1,"couve",5,6,1)

        insereAlimento(db, alimento)
    }
    @Test
    fun consegueinserirDieta(){
        val db = getWritableDatabase()

        val alimento = Alimento(1,"Couve",1,50,1)
        insereAlimento(db, alimento)

        val dieta = Dieta(1,"sugarfree","semaçucar",1)
        insereDieta(db, dieta)

        db.close()

    }


    @Test
    fun consegueAlterarDieta(){
        val db = getWritableDatabase()

        val dieta = Dieta(1,"semaçucar","Dieta sem açucar",1)
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
    fun consegueALterarAlimento(){
        val db = getWritableDatabase()

        val dieta1 = Dieta(1,"x1","x1",1)
        insereDieta(db, dieta1)

        val dieta2 = Dieta(2,"x2","x2",1)
        insereDieta(db, dieta2)

        val alimento = Alimento(1,"Alimento1",1,2,dieta1.id)
        insereAlimento(db, alimento)

        alimento.nome = "couve"
        alimento.calorias = 7
        alimento.iddieta = dieta2.id

        val registosAlterados = TabelaDBalimento(db).update(
            alimento.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf("${alimento.id}"))

        assertEquals(1,registosAlterados)
        db.close()

    }


}
