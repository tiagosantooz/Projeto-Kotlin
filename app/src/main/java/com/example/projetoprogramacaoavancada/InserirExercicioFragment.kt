package com.example.projetoprogramacaoavancada

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import com.example.projetoprogramacaoavancada.database.ContentProviderGym
import com.example.projetoprogramacaoavancada.database.TabelaDBmaquina
import com.example.projetoprogramacaoavancada.databinding.FragmentInserirExercicioBinding


class InserirExercicioFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {
    private var _binding : FragmentInserirExercicioBinding? =null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInserirExercicioBinding.inflate(inflater,container,false)
        return inflater.inflate(R.layout.fragment_inserir_exercicio,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoaderManager.getInstance(this).initLoader(ID_LOADER_MAQUINAS, null, this)

        val activity = activity as MainActivity
        activity.fragment = this

        activity.idMenuAtual = R.menu.menu_edicao
    }

    companion object {
        const val ID_LOADER_MAQUINAS = 0
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> =
    CursorLoader(
        requireContext(),
        ContentProviderGym.ENDERECO_MAQUINAS,
        TabelaDBmaquina.TODAS_COLUNAS,
        null,
        null,
        "${TabelaDBmaquina.CAMPO_NOME}"
    )


    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        TODO("Not yet implemented")
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        TODO("Not yet implemented")
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_guardar -> {
                true
            }
            R.id.action_cancelar -> {
                findNavController().navigate(R.id.action_inserirExercicioFragment_to_listaExerciciosFragment)
                true
            }
            else -> false
        }


}