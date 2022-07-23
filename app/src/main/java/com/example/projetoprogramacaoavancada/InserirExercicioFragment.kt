package com.example.projetoprogramacaoavancada

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projetoprogramacaoavancada.databinding.FragmentInserirExercicioBinding


class InserirExercicioFragment : Fragment() {
    private var _binding : FragmentInserirExercicioBinding? =null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInserirExercicioBinding.inflate(inflater,container,false)
        return inflater.inflate(R.layout.fragment_inserir_exercicio,container,false)

    }


}