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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projetoprogramacaoavancada.database.AdapterExercicios
import com.example.projetoprogramacaoavancada.database.ContentProviderGym
import com.example.projetoprogramacaoavancada.database.TabelaDBexercicio
import com.example.projetoprogramacaoavancada.databinding.FragmentListaExerciciosBinding


class ListaExerciciosFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    private var _binding: FragmentListaExerciciosBinding? = null

    private val binding get() = _binding!!

    private var adapterExercicio : AdapterExercicios? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListaExerciciosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoaderManager.getInstance(this).initLoader(ID_LOADER_EXERCICIOS, null, this)

        adapterExercicio = AdapterExercicios(this)
        binding.ReciclerViewExercicio.adapter = adapterExercicio
        binding.ReciclerViewExercicio.layoutManager = LinearLayoutManager(requireContext())

        val activity = activity as MainActivity
        activity.idMenuAtual = R.menu.menu_lista

    }


    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> =
        CursorLoader(
            requireContext(),
            ContentProviderGym.ENDERECO_EXERCICIOS,
            TabelaDBexercicio.TODAS_COLUNAS,
            null,
            null,
            "${TabelaDBexercicio.CAMPO_NOME}"
        )


    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        adapterExercicio!!.cursor = data
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        adapterExercicio!!.cursor = null
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_inserir -> {
                findNavController().navigate(R.id.action_listaExerciciosFragment_to_inserirExercicioFragment)
                true
            }
            R.id.action_alterar -> true
            R.id.action_eliminar -> true
            else -> false
        }

    companion object {
        const val ID_LOADER_EXERCICIOS = 0
    }
}