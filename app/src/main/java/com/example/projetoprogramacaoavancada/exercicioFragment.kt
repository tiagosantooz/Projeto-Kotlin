package com.example.projetoprogramacaoavancada

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.projetoprogramacaoavancada.databinding.FragmentExercicioBinding
import com.example.projetoprogramacaoavancada.databinding.FragmentTreinoBinding


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class exercicioFragment : Fragment() {


    private var _binding: FragmentExercicioBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExercicioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button5.setOnClickListener {
            findNavController().navigate(R.id.action_exercicioFragment_to_treinoFragment)
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}