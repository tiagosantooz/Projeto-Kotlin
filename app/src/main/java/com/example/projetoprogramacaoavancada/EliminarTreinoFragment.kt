package com.example.projetoprogramacaoavancada

import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.projetoprogramacaoavancada.database.ContentProviderGym
import com.example.projetoprogramacaoavancada.database.Treino
import com.example.projetoprogramacaoavancada.databinding.FragmentEliminarTreinoBinding
import com.google.android.material.snackbar.Snackbar


class EliminarTreinoFragment : Fragment() {


    private var _binding : FragmentEliminarTreinoBinding? = null

    private val binding get() = _binding!!

    private lateinit var treino: Treino

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEliminarTreinoBinding.inflate(inflater,container,false)
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

        treino = EliminarTreinoFragmentArgs.fromBundle(requireArguments()).treino

        binding.textViewDeleteDescTreino.text = treino.descricao
        binding.textViewDeleteUtiTreino.text = treino.utilizador.nome
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_eliminar -> {
                eliminaTreino()
                true
            }
            R.id.action_cancelar -> {
                voltaListaTreino()
                true
            }
            else -> false
        }

    private fun eliminaTreino() {
        val alertDialog = AlertDialog.Builder(requireContext())

        alertDialog.apply {
            setTitle("Eliminar treino")
            setMessage("Tem a certeza que pretende eliminar o treino?")
            setNegativeButton(android.R.string.cancel, DialogInterface.OnClickListener { dialogInterface, i ->  })
            setPositiveButton("Eliminar", DialogInterface.OnClickListener { dialogInterface, i -> confirmareliminaTreino()})
            show()
        }
    }

    private fun confirmareliminaTreino() {
        val enderecoTreino = Uri.withAppendedPath(ContentProviderGym.ENDERECO_TREINOS, "${treino.id}")
        val registosEliminados = requireActivity().contentResolver.delete(enderecoTreino, null, null)

        if (registosEliminados != 1) {
            Snackbar.make(
                binding.textViewDeleteDescTreino,
                "Erro eliminar treino",
                Snackbar.LENGTH_INDEFINITE
            ).show()
            return
        }

        Toast.makeText(requireContext(), "Treino eliminado com sucesso!", Toast.LENGTH_LONG).show()
        voltaListaTreino()
    }

    private fun voltaListaTreino() {
        val acao = EliminarTreinoFragmentDirections.actionEliminarTreinoFragmentToListaTreinoFragment()
        findNavController().navigate(acao)
    }

}