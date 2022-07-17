package com.example.projetoprogramacaoavancada

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.projetoprogramacaoavancada.database.Alimento
import com.example.projetoprogramacaoavancada.database.ContentProviderGym
import com.example.projetoprogramacaoavancada.databinding.FragmentEliminarAlimentoBinding
import com.google.android.material.snackbar.Snackbar


class EliminarAlimentoFragment : Fragment() {


    private var _binding: FragmentEliminarAlimentoBinding? =null
    private val binding get() = _binding!!

    private lateinit var alimento: Alimento

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       _binding = FragmentEliminarAlimentoBinding.inflate(inflater, container, false)
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

        alimento = EliminarAlimentoFragmentArgs.fromBundle(requireArguments()).alimento

        binding.textViewNomeAlimentoDelete.text = alimento.nome
        binding.textViewQuantidadeDelete.text = alimento.quantidade.toString()
        binding.textViewCaloriasDelete.text = alimento.calorias.toString()




        fun processaOpcaoMenu(item: MenuItem): Boolean =
            when (item.itemId) {
                R.id.action_eliminar -> {
                    eliminaAlimento()
                    true
                }
                R.id.action_cancelar -> {
                    voltaListaAlimentos()
                    true
                }
                else -> false
            }
    }

        private fun eliminaAlimento() {
            val enderecoAlimento = Uri.withAppendedPath(ContentProviderGym.ENDERECO_ALIMENTOS, "${alimento.id}")
            val registosEliminados = requireActivity().contentResolver.delete(enderecoAlimento, null, null)

            if (registosEliminados != 1) {
                Snackbar.make(
                    binding.textViewNomeAlimentoDelete,
                    "Erro eliminar alimento",
                    Snackbar.LENGTH_INDEFINITE
                ).show()
                return
            }

            Toast.makeText(requireContext(), "Alimento Eliminado com sucesso", Toast.LENGTH_LONG).show()
            voltaListaAlimentos()
        }

        private fun voltaListaAlimentos() {
            val acao = EliminarAlimentoFragmentDirections.actionEliminarAlimentoFragmentToListaAlimentosFragment()
            findNavController().navigate(acao)
        }


}