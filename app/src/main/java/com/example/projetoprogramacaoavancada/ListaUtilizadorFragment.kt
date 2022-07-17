package com.example.projetoprogramacaoavancada

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projetoprogramacaoavancada.databinding.FragmentListaUtilizadorBinding
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import android.database.Cursor
import android.view.MenuItem
import androidx.loader.content.CursorLoader
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projetoprogramacaoavancada.database.AdapterUtilizador
import com.example.projetoprogramacaoavancada.database.ContentProviderGym
import com.example.projetoprogramacaoavancada.database.TabelaDButilizador
import com.example.projetoprogramacaoavancada.database.Utilizador

class ListaUtilizadorFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {
    var utilizadorSeleccionado : Utilizador? = null
        get() = field
        set(value) {
            if (value != field) {
                field = value
                (requireActivity() as MainActivity).mostraOpcoesAlterarEliminar(field != null)
            }
        }
    private var _binding: FragmentListaUtilizadorBinding? = null
    private var adapterUtilizador : AdapterUtilizador? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListaUtilizadorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoaderManager.getInstance(this).initLoader(ID_LOADER_UTILIZADOR, null, this)

        adapterUtilizador = AdapterUtilizador(this)
        binding.recyclerViewUtilizador.adapter = adapterUtilizador
        binding.recyclerViewUtilizador.layoutManager = LinearLayoutManager(requireContext())

        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_lista


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> =
        CursorLoader(
            requireContext(),
            ContentProviderGym.ENDERECO_UTILIZADORES,
            TabelaDButilizador.TODAS_COLUNAS,
            null,
            null,
            "${TabelaDButilizador.CAMPO_NOME}"

        )

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        adapterUtilizador!!.cursor = data
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        adapterUtilizador!!.cursor = null
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_inserir -> {
                val acao = ListaUtilizadorFragmentDirections.actionListaUtilizadorFragmentToSecondFragment()
                findNavController().navigate(acao)
                true
            }
            R.id.action_alterar -> true
            R.id.action_eliminar -> {
                val acao = ListaUtilizadorFragmentDirections.actionListaUtilizadorFragmentToSecondFragment()
                true}
            else -> false
        }

    companion object {
        const val ID_LOADER_UTILIZADOR = 0
    }
}