package com.example.projetoprogramacaoavancada.database

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AdapterExercicios : RecyclerView.Adapter<AdapterExercicios.ViewHolderExercicio>() {
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
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolderExercicio, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}