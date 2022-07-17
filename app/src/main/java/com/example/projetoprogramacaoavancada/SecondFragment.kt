package com.example.projetoprogramacaoavancada

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.projetoprogramacaoavancada.databinding.FragmentSecondBinding
import android.view.MenuItem
import android.widget.Toast
import com.example.projetoprogramacaoavancada.database.ContentProviderGym
import com.example.projetoprogramacaoavancada.database.Utilizador
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var utilizador: Utilizador? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity() as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_edicao

        if (arguments != null){
            utilizador = SecondFragmentArgs.fromBundle(requireArguments()).utilizador

            if(utilizador!=null){

                binding.editTextTextPersonName.setText(utilizador!!.nome)
                binding.editTextTextEmailAddress.setText(utilizador!!.sexo)
                binding.editTextNumber.setText((utilizador!!.idade).toString())
                binding.editTextNumber2.setText((utilizador!!.peso).toString())
                binding.editTextNumber3.setText((utilizador!!.altura).toString())

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
                voltaListaUtilizadores()
                true
            }
            else -> false
        }

    private fun guardar() {

        val nome = binding.editTextTextPersonName.text.toString()
        if (nome.isBlank()){
            binding.editTextTextPersonName.error = "Nome obrigatório"
            binding.editTextTextPersonName.requestFocus()
            return
        }

        val sexo = binding.editTextTextEmailAddress.text.toString()
        if (sexo.isBlank()){
            binding.editTextTextEmailAddress.error = "Sexo obrigatório"
            binding.editTextTextEmailAddress.requestFocus()
            return
        }

        val idade = binding.editTextNumber.text.toString()
        if (idade.isBlank()){
            binding.editTextNumber.error = "Idade obrigatória"
            binding.editTextNumber.requestFocus()
            return
        }

        val peso = binding.editTextNumber2.text.toString()
        if(peso.isBlank()){
            binding.editTextNumber2.error="Peso obrigatório"
            binding.editTextNumber2.requestFocus()
            return
        }

        val altura = binding.editTextNumber3.text.toString()
        if(altura.isBlank()){
            binding.editTextNumber3.error="Altura obrigatória"
            binding.editTextNumber3.requestFocus()
            return
        }

        val utilizadorGuardado =
            if(utilizador==null){
                insereUtilizador(nome,sexo,idade,peso,altura)
            } else{
                alteraUtilizador(nome,sexo,idade,peso,altura)
            }

        if(utilizadorGuardado){
            Toast.makeText(requireContext(), "Utilizador guardado com sucesso", Toast.LENGTH_LONG)
                .show()
            voltaListaUtilizadores()
        } else{
            Snackbar.make(binding.editTextTextPersonName, "Erro guardar utilizador", Snackbar.LENGTH_INDEFINITE).show()
            return
        }


    }

    private fun insereUtilizador(nome: String, sexo: String, idade: String, peso: String, altura: String): Boolean {
        val utilizador = Utilizador(nome,sexo,idade.toLong(),peso.toLong(),altura.toLong())

        val enderecoUtilizadorInserido = requireActivity().contentResolver.insert(ContentProviderGym.ENDERECO_UTILIZADORES, utilizador.toContentValues())

       return enderecoUtilizadorInserido != null

    }

    private fun alteraUtilizador (nome: String, sexo: String,idade: String, peso: String, altura: String): Boolean {
        val utilizador = Utilizador(nome,sexo,idade.toLong(),peso.toLong(),altura.toLong())

        val enderecoUtilizador = Uri.withAppendedPath(ContentProviderGym.ENDERECO_UTILIZADORES, "${this.utilizador!!.id}")

        val registosAlterados = requireActivity().contentResolver.update(enderecoUtilizador, utilizador.toContentValues(),null,null)

        return registosAlterados == 1

    }

    private fun voltaListaUtilizadores(){
        findNavController().navigate(R.id.action_SecondFragment_to_listaUtilizadorFragment)
    }

}