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

import com.example.projetoprogramacaoavancada.database.Exercicio
import com.example.projetoprogramacaoavancada.databinding.FragmentEliminarExercicioBinding
import com.google.android.material.snackbar.Snackbar


class EliminarExercicioFragment : Fragment() {


    private var _binding : FragmentEliminarExercicioBinding? = null

    private val binding get() = _binding!!

    private lateinit var exercicio: Exercicio

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEliminarExercicioBinding.inflate(inflater,container,false)
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

        exercicio = EliminarExercicioFragmentArgs.fromBundle(requireArguments()).exercicio

        binding.textViewDeleteNameEx.text = exercicio.nome
        binding.textViewDeleteDescEx.text = exercicio.descricao
        binding.textViewDeleteCargaEx.text = exercicio.carga.toString()
        binding.textViewDeleteRepEx.text = exercicio.repeticoes.toString()
        binding.textViewDeleteMaquinaEx.text = exercicio.maquina.nome
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_eliminar -> {
                eliminaExercicio()
                true
            }
            R.id.action_cancelar -> {
             voltaListaExercicio()
                true
            }
            else -> false
        }

    private fun eliminaExercicio() {
        val alertDialog = AlertDialog.Builder(requireContext())

        alertDialog.apply {
            setTitle("Eliminar exercicio")
            setMessage("Tem a certeza que pretende eliminar o exercicio?")
            setNegativeButton(android.R.string.cancel, DialogInterface.OnClickListener { dialogInterface, i ->  })
            setPositiveButton("Eliminar", DialogInterface.OnClickListener { dialogInterface, i -> confirmareliminaExercicio()})
            show()
        }
    }

    private fun confirmareliminaExercicio() {
        val enderecoExercicio = Uri.withAppendedPath(ContentProviderGym.ENDERECO_EXERCICIOS, "${exercicio.id}")
        val registosEliminados = requireActivity().contentResolver.delete(enderecoExercicio, null, null)

        if (registosEliminados != 1) {
            Snackbar.make(
                binding.textViewDeleteNameEx,
                "Erro eliminar exercicio",
                Snackbar.LENGTH_INDEFINITE
            ).show()
            return
        }

        Toast.makeText(requireContext(), "Exercicio eliminado com sucesso!", Toast.LENGTH_LONG).show()
        voltaListaExercicio()
    }

    private fun voltaListaExercicio() {
        val acao = EliminarExercicioFragmentDirections.actionEliminarExercicioFragmentToListaExerciciosFragment()
        findNavController().navigate(acao)
    }

}