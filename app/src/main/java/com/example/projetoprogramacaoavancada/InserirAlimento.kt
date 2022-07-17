package com.example.projetoprogramacaoavancada

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




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding= FragmentInserirAlimentoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity() as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_edicao
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
    when(item.itemId) {
        R.id.action_guardar -> {
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
            binding.editTextAlimentName.error = "Quantidade obrigatória"
            binding.editTextAlimentName.requestFocus()
            return
        }

        val caloriaalimento = binding.editTextNumberCalories.text.toString()
        if (caloriaalimento.isBlank()){
            binding.editTextAlimentName.error = "Calorias obrigatórias"
            binding.editTextAlimentName.requestFocus()
            return
        }

        insereAlimento(nomealimento,quantidadealimento,caloriaalimento)
    }

    private fun insereAlimento(nome: String, quantidade: String, calorias: String) {
        val alimento = Alimento(nome, quantidade.toInt(), calorias.toInt())

        val enderecoAlimentoInserido = requireActivity().contentResolver.insert(ContentProviderGym.ENDERECO_ALIMENTOS, alimento.toContentValues())

        if(enderecoAlimentoInserido == null){
            Snackbar.make(binding.editTextAlimentName, "Erro guardar alimento", Snackbar.LENGTH_INDEFINITE).show()
            return
        }

        Toast.makeText(requireContext(),"Alimento guardado com sucesso", Toast.LENGTH_LONG).show()
        voltaListaAlimentos()
    }

    private fun voltaListaAlimentos() {
        findNavController().navigate(R.id.action_inserirAlimento_to_listaAlimentosFragment)
    }
}