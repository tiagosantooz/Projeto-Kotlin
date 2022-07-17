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
import com.example.projetoprogramacaoavancada.database.AdapterAlimento
import com.example.projetoprogramacaoavancada.database.Alimento
import com.example.projetoprogramacaoavancada.database.ContentProviderGym
import com.example.projetoprogramacaoavancada.database.TabelaDBalimento
import com.example.projetoprogramacaoavancada.databinding.FragmentListaAlimentosBinding


class ListaAlimentosFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor>  {

    var alimentoSeleccionado : Alimento? = null
        get() = field
        set(value) {
            if (value != field) {
                field = value
                (requireActivity() as MainActivity).mostraOpcoesAlterarEliminar(field != null)
            }
        }

    private var _binding : FragmentListaAlimentosBinding? = null
    private var adapterAlimento : AdapterAlimento? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListaAlimentosBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoaderManager.getInstance(this).initLoader(ID_LOADER_ALIMENTOS, null, this)

        adapterAlimento = AdapterAlimento(this)
        binding.RecyclerViewAlimento.adapter = adapterAlimento
        binding.RecyclerViewAlimento.layoutManager = LinearLayoutManager(requireContext())

        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_lista
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> =
        CursorLoader(
            requireContext(),
            ContentProviderGym.ENDERECO_ALIMENTOS,
            TabelaDBalimento.TODAS_COLUNAS,
            null,
            null,
            "${TabelaDBalimento.CAMPO_NOME_ALIMENTO}"
        )


    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {

    }

    override fun onLoaderReset(loader: Loader<Cursor>) {

    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_inserir -> {
                val acao = ListaAlimentosFragmentDirections.actionListaAlimentosFragmentToInserirAlimento()
                findNavController().navigate(acao)
                true
            }
            R.id.action_alterar -> true
            R.id.action_eliminar -> {
                val acao = ListaAlimentosFragmentDirections.actionListaAlimentosFragmentToEliminarAlimentoFragment(alimentoSeleccionado!!)
                findNavController().navigate(acao)
                true
            }
            else -> false
        }
    companion object {
        const val ID_LOADER_ALIMENTOS = 0
    }


}