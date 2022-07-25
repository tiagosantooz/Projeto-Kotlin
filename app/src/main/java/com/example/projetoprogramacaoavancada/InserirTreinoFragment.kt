package com.example.projetoprogramacaoavancada

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleCursorAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import com.example.projetoprogramacaoavancada.database.ContentProviderGym
import com.example.projetoprogramacaoavancada.database.TabelaDButilizador
import com.example.projetoprogramacaoavancada.database.Treino
import com.example.projetoprogramacaoavancada.database.Utilizador
import com.example.projetoprogramacaoavancada.databinding.FragmentInserirTreinoBinding
import com.google.android.material.snackbar.Snackbar


class InserirTreinoFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    private var _binding : FragmentInserirTreinoBinding? =null

    private val binding get() = _binding!!

    private var treino : Treino? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentInserirTreinoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoaderManager.getInstance(this).initLoader(ID_LOADER_UTILIZADORES, null, this)

        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_edicao

        if ( arguments!= null){
            treino = InserirTreinoFragmentArgs.fromBundle(requireArguments()).treino

            if (treino != null){
                binding.editTextDescTreino.setText(treino!!.descricao)
            }

        }
        LoaderManager.getInstance(this).initLoader(ID_LOADER_UTILIZADORES, null, this)

    }

    companion object {
        const val ID_LOADER_UTILIZADORES = 0
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
        val adapterUtilizador = SimpleCursorAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            data,
            arrayOf(TabelaDButilizador.CAMPO_NOME),
            intArrayOf(android.R.id.text1),
            0
        )

        binding.spinnerUtiTreino.adapter = adapterUtilizador

        atualizaUtilizadorSelecionado()
    }

    private fun atualizaUtilizadorSelecionado() {
        if (treino == null) return
        val idUtilizador = treino!!.utilizador.id

        val ultimoUtilizador = binding.spinnerUtiTreino.count - 1

        for (i in 0..ultimoUtilizador) {
            if (binding.spinnerUtiTreino.getItemIdAtPosition(i) == idUtilizador) {
                binding.spinnerUtiTreino.setSelection(i)
                return
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        if (_binding == null ) return
        binding.spinnerUtiTreino.adapter = null
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_guardar -> {
                guardar()
                true
            }
            R.id.action_cancelar -> {
                voltaListaTreino()
                true
            }
            else -> false
        }

    private fun guardar(){

        val descricao = binding.editTextDescTreino.text.toString()
        if (descricao.isBlank()){
            binding.editTextDescTreino.error = "Descrição obrigatória"
            binding.editTextDescTreino.requestFocus()
            return
        }

        val utilizador = binding.spinnerUtiTreino.selectedItemId
        if (utilizador == Spinner.INVALID_ROW_ID){
            binding.textView3.error = "Utilizador obrigatório"
            binding.textView3.requestFocus()
            return
        }

        val treinoGuardado =
            if(treino == null){
                insereTreino(descricao, utilizador)
            }
            else {
                alteraExercicio(descricao, utilizador)
            }

        if(treinoGuardado){
            Toast.makeText(requireContext(), "Treino guardado com sucesso!", Toast.LENGTH_LONG)
                .show()
            voltaListaTreino()
        } else {
            Snackbar.make(binding.editTextDescTreino, "Erro guardar treino", Snackbar.LENGTH_INDEFINITE).show()
            return
        }
    }


    private fun insereTreino(descricao: String, utilizador : Long): Boolean {
        val treino = Treino(descricao, Utilizador(id = utilizador))

        val enderecoTreinoInserido = requireActivity().contentResolver.insert(ContentProviderGym.ENDERECO_TREINOS, treino.toContentValues())

        return enderecoTreinoInserido != null
    }
    private fun alteraExercicio(descricao: String, utilizador : Long) : Boolean {
        val treino = Treino(descricao, Utilizador(id = utilizador))

        val enderecoTreino = Uri.withAppendedPath(ContentProviderGym.ENDERECO_TREINOS, "${this.treino!!.id}")

        val registosAlterados = requireActivity().contentResolver.update(enderecoTreino, treino.toContentValues(), null, null)

        return registosAlterados == 1
    }


    private fun voltaListaTreino() {
        findNavController().navigate(R.id.action_inserirTreinoFragment_to_listaTreinoFragment)
    }
}