package com.example.projetoprogramacaoavancada

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AdapterUtilizador : RecyclerView.Adapter<AdapterUtilizador.ViewHolderUtilizador>() {
    var cursor: Cursor? = null
        get() = field
        set(value) {
            if(field != value){
                field = value
                notifyDataSetChanged()
            }
        }

    class ViewHolderUtilizador(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderUtilizador {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolderUtilizador, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        if (cursor == null) return 0

        return cursor!!.count
    }

}