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
import com.example.projetoprogramacaoavancada.database.*
import com.example.projetoprogramacaoavancada.databinding.FragmentListaExerciciosBinding
import com.example.projetoprogramacaoavancada.databinding.FragmentListaTreinoBinding


class ListaTreinoFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {


    var treinoSelecionado : Treino? = null
        get() = field
        set(value){
            field = value
            (requireActivity() as MainActivity).mostraOpcoesAlterarEliminar(field != null)
        }
    private var _binding: FragmentListaTreinoBinding? = null

    private val binding get() = _binding!!

    private var adapterTreino : AdapterTreino? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListaTreinoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoaderManager.getInstance(this).initLoader(ID_LOADER_TREINOS, null, this)

        adapterTreino =  AdapterTreino(this)
        binding.RecyclerViewTreino.adapter = adapterTreino
        binding.RecyclerViewTreino.layoutManager = LinearLayoutManager(requireContext())

        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_lista

    }


    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> =
        CursorLoader(
            requireContext(),
            ContentProviderGym.ENDERECO_EXERCICIOS,
            TabelaDBexercicio.TODAS_COLUNAS,
            null,
            null,
            "${TabelaDBtreino.CAMPO_DESCRICAO}"
        )


    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        adapterTreino!!.cursor = data
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        if (binding == null) return
        adapterTreino!!.cursor = null
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_inserir -> {
                val acao = ListaTreinoFragmentDirections.actionListaTreinoFragmentToInserirTreinoFragment(null)
                findNavController().navigate(acao)
                (activity as MainActivity).atualizaTitulo("Inserir Treino")
                true
            }
            R.id.action_alterar -> {
                val acao = ListaTreinoFragmentDirections.actionListaTreinoFragmentToInserirTreinoFragment(treinoSelecionado)
                findNavController().navigate(acao)
                (activity as MainActivity).atualizaTitulo("Alterar Treino")
                true
            }
            R.id.action_eliminar -> {
                val acao = ListaTreinoFragmentDirections.actionListaTreinoFragmentToEliminarTreinoFragment(treinoSelecionado!!)
                findNavController().navigate(acao)
                (activity as MainActivity).atualizaTitulo("Eliminar Treino")
                true
            }
            else -> false
        }

    companion object {
        const val ID_LOADER_TREINOS = 0
    }
}