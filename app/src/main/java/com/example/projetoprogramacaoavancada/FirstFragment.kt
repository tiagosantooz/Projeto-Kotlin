package com.example.projetoprogramacaoavancada

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.projetoprogramacaoavancada.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_listaUtilizadorFragment)
            (activity as MainActivity).atualizaTitulo("Lista de Utilizadores")
        }

        binding.button2.setOnClickListener{
            findNavController().navigate(R.id.action_FirstFragment_to_treinoFragment)
            (activity as MainActivity).atualizaTitulo("Lista de Treinos")
        }

        binding.buttonAliment.setOnClickListener{
            findNavController().navigate(R.id.action_FirstFragment_to_listaAlimentosFragment)
            (activity as MainActivity).atualizaTitulo("Lista de Alimentos")
        }

        binding.buttonExercise.setOnClickListener{
            findNavController().navigate(R.id.action_FirstFragment_to_listaExerciciosFragment)

        }

        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_main
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_settings -> true
            else -> false
        }
}