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
import androidx.loader.content.CursorLoader

class ListaUtilizadorFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    private var _binding: FragmentListaUtilizadorBinding? = null

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
        TODO("Not yet implemented")
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        TODO("Not yet implemented")
    }

    companion object {
        const val ID_LOADER_UTILIZADOR = 0
    }
}