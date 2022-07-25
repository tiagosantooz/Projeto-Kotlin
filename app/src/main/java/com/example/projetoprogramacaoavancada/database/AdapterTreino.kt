package com.example.projetoprogramacaoavancada.database

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projetoprogramacaoavancada.ListaExerciciosFragment
import com.example.projetoprogramacaoavancada.ListaTreinoFragment
import com.example.projetoprogramacaoavancada.R

class AdapterTreino (val fragment: ListaTreinoFragment): RecyclerView.Adapter<AdapterTreino.ViewHolderTreino>() {
    var cursor: Cursor? = null
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyDataSetChanged()
            }
        }

    var viewHolderSelecionado : ViewHolderTreino? = null

    inner class ViewHolderTreino(itemTreino: View) : RecyclerView.ViewHolder(itemTreino), View.OnClickListener{
        val textViewDesc = itemTreino.findViewById<TextView>(R.id.textViewDescTreino)
        val textViewUti = itemTreino.findViewById<TextView>(R.id.textViewUtTreino)


        init{
            itemTreino.setOnClickListener(this)
        }
        var treino : Treino? = null
            get() = field
            set(value : Treino?){
                field = value

                textViewDesc.text = treino?.descricao ?:""
                textViewUti.text = treino?.utilizador?.nome ?:""
            }

        override fun onClick(v: View?) {
            viewHolderSelecionado?.desseleciona()
            seleciona()
        }

        private fun seleciona() {
            itemView.setBackgroundResource(android.R.color.holo_orange_light)
            viewHolderSelecionado = this
            fragment.treinoSelecionado = treino
        }

        private fun desseleciona() {
            itemView.setBackgroundResource(android.R.color.white)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTreino {
        val itemTreino = fragment.layoutInflater.inflate(R.layout.item_treino,parent,false)
        return ViewHolderTreino(itemTreino)
    }

    override fun onBindViewHolder(holder: ViewHolderTreino, position: Int) {
        cursor!!.moveToPosition(position)
        holder.treino = Treino.fromCursor(cursor!!)
    }

    override fun getItemCount(): Int {
        if (cursor == null) return 0

        return cursor!!.count
    }

}