package com.example.projetoprogramacaoavancada.database

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projetoprogramacaoavancada.ListaAlimentosFragment
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
    var viewHolderSelecionado : AdapterAlimento.ViewHolderAlimento? = null
    inner class ViewHolderAlimento(itemAlimento: View) : RecyclerView.ViewHolder(itemAlimento), View.OnClickListener{
        val textNameAlimento = itemAlimento.findViewById<TextView>(R.id.textNameAliment)
        val textQuantity = itemAlimento.findViewById<TextView>(R.id.textQuantity)
        val textCalories = itemAlimento.findViewById<TextView>(R.id.textCalories)

        init{
            itemAlimento.setOnClickListener(this)
        }
        var alimento : Alimento? = null
            get()=field
            set(value: Alimento?) {
                field = value

                textNameAlimento.text = alimento?.nome ?: ""
                textCalories.text = (alimento?.calorias ?: "").toString()
                textQuantity.text = (alimento?.quantidade?: "").toString()
            }

        override fun onClick(v: View?) {
            viewHolderSelecionado?.desseleciona()
            seleciona()
        }

        private fun seleciona() {
            itemView.setBackgroundResource(android.R.color.holo_orange_light)
            viewHolderSelecionado = this
            fragment.alimentoSeleccionado = alimento
        }

        private fun desseleciona() {
            itemView.setBackgroundResource(android.R.color.white)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAlimento {
        val itemAlimento = fragment.layoutInflater.inflate(R.layout.item_alimento, parent, false)
        return ViewHolderAlimento(itemAlimento)
    }

    override fun onBindViewHolder(holder: ViewHolderAlimento, position: Int) {
        cursor!!.moveToPosition(position)
        holder.alimento = Alimento.fromCursor(cursor!!)
    }

    override fun getItemCount(): Int {
        if (cursor == null) return 0

        return cursor!!.count
    }
}