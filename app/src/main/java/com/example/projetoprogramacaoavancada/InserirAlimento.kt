package com.example.projetoprogramacaoavancada

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.loader.app.LoaderManager
import androidx.navigation.fragment.findNavController
import com.example.projetoprogramacaoavancada.database.Alimento
import com.example.projetoprogramacaoavancada.database.ContentProviderGym
import com.example.projetoprogramacaoavancada.databinding.FragmentInserirAlimentoBinding
import com.google.android.material.snackbar.Snackbar

class InserirAlimento : Fragment() {

private var _binding: FragmentInserirAlimentoBinding? = null

private val binding get() = _binding!!

private var alimento: Alimento? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding= FragmentInserirAlimentoBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity() as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_edicao

        if(arguments != null){
            alimento = InserirAlimentoArgs.fromBundle(requireArguments()).alimento

            if(alimento!=null){
                binding.editTextAlimentName.setText(alimento!!.nome)
                binding.editTextNumberQuantity.setText((alimento!!.quantidade).toString())
                binding.editTextNumberCalories.setText((alimento!!.calorias).toString())

            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun processaOpcaoMenu(item: MenuItem) : Boolean =
    when(item.itemId) {
        R.id.action_guardar -> {
            guardar()
            true
        }
        R.id.action_cancelar -> {
            findNavController().navigate(R.id.action_inserirAlimento_to_listaAlimentosFragment)
            true
        }
        else -> false
    }

    private fun guardar(){
        val nomealimento = binding.editTextAlimentName.text.toString()
        if (nomealimento.isBlank()){
            binding.editTextAlimentName.error = "Nome obrigatório"
            binding.editTextAlimentName.requestFocus()
            return
        }

        val quantidadealimento = binding.editTextNumberQuantity.text.toString()
        if (quantidadealimento.isBlank()){
            binding.editTextNumberQuantity.error = "Quantidade obrigatória"
            binding.editTextNumberQuantity.requestFocus()
            return
        }

        val caloriaalimento = binding.editTextNumberCalories.text.toString()
        if (caloriaalimento.isBlank()){
            binding.editTextNumberQuantity.error = "Calorias obrigatórias"
            binding.editTextNumberQuantity.requestFocus()
            return
        }

        val alimentoGuardado =
            if(alimento==null){
                insereAlimento(nomealimento,quantidadealimento,caloriaalimento)
            } else {
                alteraAlimento(nomealimento,quantidadealimento,caloriaalimento)
            }

        if (alimentoGuardado) {
            Toast.makeText(requireContext(), "livro guardado com sucesso", Toast.LENGTH_LONG)
                .show()
            voltaListaAlimentos()
        } else {
            Snackbar.make(binding.editTextAlimentName, "erro guardar livro", Snackbar.LENGTH_INDEFINITE).show()
            return
        }
    }

    private fun insereAlimento(nome: String, quantidade: String, calorias: String): Boolean {
        val alimento = Alimento(nome, quantidade.toInt(), calorias.toInt())

        val enderecoAlimentoInserido = requireActivity().contentResolver.insert(ContentProviderGym.ENDERECO_ALIMENTOS, alimento.toContentValues())

        return enderecoAlimentoInserido!= null
    }

    private fun alteraAlimento(nome: String, quantidade: String, calorias: String) : Boolean {
        val alimento = Alimento(nome,quantidade.toInt(),calorias.toInt())

        val enderecoAlimento = Uri.withAppendedPath(ContentProviderGym.ENDERECO_ALIMENTOS, "${this.alimento!!.id}")

        val registosAlterados = requireActivity().contentResolver.update(enderecoAlimento, alimento.toContentValues(), null, null)

        return registosAlterados == 1
    }

    private fun voltaListaAlimentos() {
        findNavController().navigate(R.id.action_inserirAlimento_to_listaAlimentosFragment)
    }
}