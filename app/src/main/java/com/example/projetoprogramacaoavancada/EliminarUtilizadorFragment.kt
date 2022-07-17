package com.example.projetoprogramacaoavancada

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projetoprogramacaoavancada.databinding.FragmentEliminarUtilizadorBinding
import com.example.projetoprogramacaoavancada.database.Utilizador


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


}