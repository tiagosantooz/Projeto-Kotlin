package com.example.projetoprogramacaoavancada

import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.example.projetoprogramacaoavancada.database.ContentProviderGym
import com.example.projetoprogramacaoavancada.databinding.FragmentEliminarUtilizadorBinding
import com.example.projetoprogramacaoavancada.database.Utilizador
import com.google.android.material.snackbar.Snackbar


class EliminarUtilizadorFragment : Fragment() {

    private var _binding: FragmentEliminarUtilizadorBinding? = null

    private val binding get() = _binding!!

    private lateinit var utilizador: Utilizador

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentEliminarUtilizadorBinding.inflate(inflater, container, false)
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
        activity.idMenuAtual = R.menu.menu_eliminar

        utilizador = EliminarUtilizadorFragmentArgs.fromBundle(requireArguments()).utilizador

        binding.textViewNome.text = utilizador.nome
        binding.textViewSexo.text = utilizador.sexo
        binding.textViewAltura.text = utilizador.altura.toString()
        binding.textViewPeso.text = utilizador.peso.toString()
        binding.textViewIdade.text = utilizador.idade.toString()

    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_eliminar -> {
                eliminaUtilizador()
                (activity as MainActivity).atualizaTitulo("Lista de Utilizadores")
                true
            }
            R.id.action_cancelar -> {
                voltaListaUtilizador()
                (activity as MainActivity).atualizaTitulo("Lista de Utilizadores")
                true
            }
            else -> false
        }
    private fun eliminaUtilizador() {
        val alertDialog = AlertDialog.Builder(requireContext())

        alertDialog.apply {
            setTitle("Eliminar Utilizador")
            setMessage("Tem a certeza que pretende eliminar o Utilizador")
            setNegativeButton(android.R.string.cancel, DialogInterface.OnClickListener { dialogInterface, i ->  })
            setPositiveButton("Eliminar", DialogInterface.OnClickListener { dialogInterface, i -> confirmaeliminaUtilizador() })
            show()
        }
    }

    private fun confirmaeliminaUtilizador(){
        val enderecoUtilizador = Uri.withAppendedPath(ContentProviderGym.ENDERECO_UTILIZADORES, "${utilizador.id}")
        val registosEliminados = requireActivity().contentResolver.delete(enderecoUtilizador,null,null)

        if(registosEliminados!=1){
            Snackbar.make(
                binding.textViewNome,
                "Erro eliminar utilizador",
                Snackbar.LENGTH_INDEFINITE
            ).show()
            return
        }

        Toast.makeText(requireContext(), "Livro eliminado com sucesso", Toast.LENGTH_LONG).show()
        voltaListaUtilizador()
    }

    private fun voltaListaUtilizador(){
        val acao = EliminarUtilizadorFragmentDirections.actionEliminarUtilizadorFragmentToListaUtilizadorFragment()
        findNavController().navigate(acao)
    }
}