package com.example.projetoprogramacaoavancada.database

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projetoprogramacaoavancada.ListaAlimentosFragment
import com.example.projetoprogramacaoavancada.ListaUtilizadorFragment
import com.example.projetoprogramacaoavancada.R

class AdapterAlimento(val fragment: ListaAlimentosFragment) : RecyclerView.Adapter<AdapterAlimento.ViewHolderAlimento>(){

    var cursor: Cursor? = null
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyDataSetChanged()
            }
        }

    class ViewHolderAlimento(itemAlimento: View) : RecyclerView.ViewHolder(itemAlimento){
        val textNameAlimento = itemAlimento.findViewById<TextView>(R.id.textName)
        val textQuantity = itemAlimento.findViewById<TextView>(R.id.textQuantity)
        val textCalories = itemAlimento.findViewById<TextView>(R.id.textCalories)

        var alimento : Alimento? = null
            get()=field
            set(value: Alimento?){
                field = value

                textNameAlimento.text = alimento?.nome?: ""
                textCalories.text = (alimento?.calorias ?: "").toString()
                textQuantity.text = (alimento?.quantidade?: "").toString()
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAlimento {
        val itemAlimento = fragment.layoutInflater.inflate(R.layout.item_alimento, parent, false)
        return ViewHolderAlimento(itemAlimento)
    }

    override fun onBindViewHolder(holder: ViewHolderAlimento, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        if (cursor == null) return 0

        return cursor!!.count
    }
}