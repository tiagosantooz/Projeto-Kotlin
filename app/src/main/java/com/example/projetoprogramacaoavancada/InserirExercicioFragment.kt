package com.example.projetoprogramacaoavancada

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleCursorAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import com.example.projetoprogramacaoavancada.database.ContentProviderGym
import com.example.projetoprogramacaoavancada.database.Exercicio
import com.example.projetoprogramacaoavancada.database.Maquina
import com.example.projetoprogramacaoavancada.database.TabelaDBmaquina
import com.example.projetoprogramacaoavancada.databinding.FragmentInserirExercicioBinding
import com.google.android.material.snackbar.Snackbar


class InserirExercicioFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    private var _binding : FragmentInserirExercicioBinding? =null

    private val binding get() = _binding!!

    private var exercicio : Exercicio? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentInserirExercicioBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoaderManager.getInstance(this).initLoader(ID_LOADER_MAQUINAS, null, this)

        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_edicao

        if ( arguments!= null){
            exercicio = InserirExercicioFragmentArgs.fromBundle(requireArguments()).exercicio

            if (exercicio != null){
                binding.editTextNameEx.setText(exercicio!!.nome)
                binding.editTextDescEx.setText(exercicio!!.descricao)
                binding.editTextRepEx.setText((exercicio!!.repeticoes).toString())
                binding.editTextCargaEx.setText((exercicio!!.carga.toString()))
            }

        }
        LoaderManager.getInstance(this).initLoader(ID_LOADER_MAQUINAS, null, this)

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
        val adapterMaquina = SimpleCursorAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            data,
            arrayOf(TabelaDBmaquina.CAMPO_NOME),
            intArrayOf(android.R.id.text1),
            0
        )

        binding.spinnerMaquina.adapter = adapterMaquina
    }

    private fun atualizaMaquinaSelecionada() {
        if (exercicio == null) return
        val idMaquina = exercicio!!.maquina.id

        val ultimaMaquina = binding.spinnerMaquina.count - 1

        for (i in 0..ultimaMaquina) {
            if (binding.spinnerMaquina.getItemIdAtPosition(i) == idMaquina) {
                binding.spinnerMaquina.setSelection(i)
                return
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        if (binding == null )return
        binding.spinnerMaquina.adapter = null
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_guardar -> {
                guardar()
                true
            }
            R.id.action_cancelar -> {
                voltaListaExercicio()
                true
            }
            else -> false
        }

    private fun guardar(){
        val nome = binding.editTextNameEx.text.toString()
        if (nome.isBlank()){
            binding.editTextNameEx.error = "Nome obrigatório"
            binding.editTextNameEx.requestFocus()
            return
        }

        val descricao = binding.editTextDescEx.text.toString()
        if (descricao.isBlank()){
            binding.editTextDescEx.error = "Descrição obrigatória"
            binding.editTextDescEx.requestFocus()
            return
        }

        val carga = binding.editTextCargaEx.text.toString()
        if (carga.isBlank()){
            binding.editTextCargaEx.error = "Carga obrigatória"
            binding.editTextCargaEx.requestFocus()
            return
        }

        val repeticao = binding.editTextRepEx.text.toString()
        if (descricao.isBlank()){
            binding.editTextRepEx.error = "Repetições obrigatórias"
            binding.editTextRepEx.requestFocus()
            return
        }

        val maquina = binding.spinnerMaquina.selectedItemId
        if (maquina == Spinner.INVALID_ROW_ID){
            binding.textView16.error = "Categoria obrigatória"
            binding.textView16.requestFocus()
            return
        }

        val exercicioGuardado =
            if(exercicio == null){
                insereExercicio(nome, carga, maquina, repeticao, descricao)
            }
            else {
                alteraExercicio(nome, carga, maquina, repeticao, descricao)
            }

        if(exercicioGuardado){
            Toast.makeText(requireContext(), "Exercicio guardado com sucesso!", Toast.LENGTH_LONG)
                .show()
            voltaListaExercicio()
        } else {
            Snackbar.make(binding.editTextNameEx, "Erro guardar livro", Snackbar.LENGTH_INDEFINITE).show()
            return
        }
        }


    private fun insereExercicio(nome: String, carga: String, maquina : Long, repeticao: String, descricao : String) : Boolean {
        val exercicio = Exercicio(nome, descricao, Maquina(id = maquina),carga.toLong(), repeticao.toLong())

        val enderecoExercicioInserido = requireActivity().contentResolver.insert(ContentProviderGym.ENDERECO_EXERCICIOS, exercicio.toContentValues())

        return enderecoExercicioInserido != null
    }
    private fun alteraExercicio(nome: String, carga: String, maquina : Long, repeticao: String, descricao : String) : Boolean {
        val exercicio = Exercicio(nome, descricao, Maquina(id = maquina),carga.toLong(), repeticao.toLong())

        val enderecoExercicio = Uri.withAppendedPath(ContentProviderGym.ENDERECO_EXERCICIOS, "${this.exercicio!!.id}")

        val registosAlterados = requireActivity().contentResolver.update(enderecoExercicio, exercicio.toContentValues(), null, null)

        return registosAlterados == 1
    }

    private fun voltaListaExercicio() {
        findNavController().navigate(R.id.action_inserirExercicioFragment_to_listaExerciciosFragment)
    }
}