package com.example.projetoprogramacaoavancada.database

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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

    var viewHolderSelecionado : ViewHolderExercicio? = null

    inner class ViewHolderExercicio(itemExercicio: View) : RecyclerView.ViewHolder(itemExercicio), View.OnClickListener{
        val textViewNome = itemExercicio.findViewById<TextView>(R.id.textViewNomeEx)
        val textViewDesc = itemExercicio.findViewById<TextView>(R.id.textViewDescEx)
        val textViewCarga = itemExercicio.findViewById<TextView>(R.id.textViewCargEx)
        val textViewRep = itemExercicio.findViewById<TextView>(R.id.textViewRepEx)
        val textViewMaquina = itemExercicio.findViewById<TextView>(R.id.textViewNomeMaqEx)


        init{
            itemExercicio.setOnClickListener(this)
        }
        var exercicio : Exercicio? = null
            get() = field
            set(value : Exercicio?){
                field = value

                textViewNome.text = exercicio?.nome ?:""
                textViewDesc.text = exercicio?.descricao ?:""
                textViewCarga.text = (exercicio?.carga ?:"").toString()
                textViewRep.text = (exercicio?.repeticoes ?:"").toString()
                textViewMaquina.text = exercicio?.maquina?.nome ?:""
            }

        override fun onClick(v: View?) {
            viewHolderSelecionado?.desseleciona()
            seleciona()
        }

        private fun seleciona() {
            itemView.setBackgroundResource(android.R.color.holo_orange_light)
            viewHolderSelecionado = this
            fragment.exercicioSelecionado = exercicio
        }

        private fun desseleciona() {
            itemView.setBackgroundResource(android.R.color.white)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderExercicio {
        val itemExercicio = fragment.layoutInflater.inflate(R.layout.item_exercicio,parent,false)
        return ViewHolderExercicio(itemExercicio)
    }

    override fun onBindViewHolder(holder: ViewHolderExercicio, position: Int) {
   cursor!!.moveToPosition(position)
        holder.exercicio = Exercicio.fromCursor(cursor!!)
    }

    override fun getItemCount(): Int {
        if (cursor == null) return 0

        return cursor!!.count
    }

}