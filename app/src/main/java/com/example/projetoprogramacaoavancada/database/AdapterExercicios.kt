package com.example.projetoprogramacaoavancada.database

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projetoprogramacaoavancada.ListaExerciciosFragment
import com.example.projetoprogramacaoavancada.R

class AdapterExercicios (val fragment: ListaExerciciosFragment): RecyclerView.Adapter<AdapterExercicios.ViewHolderExercicio>() {
    var cursor: Cursor? = null
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyDataSetChanged()
            }
        }


    class ViewHolderExercicio(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderExercicio {
        val itemExercicio = fragment.layoutInflater.inflate(R.layout.item_exercicio,parent,false)
        return ViewHolderExercicio(itemExercicio)
    }

    override fun onBindViewHolder(holder: ViewHolderExercicio, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        if (cursor == null) return 0

        return cursor!!.count
    }

}