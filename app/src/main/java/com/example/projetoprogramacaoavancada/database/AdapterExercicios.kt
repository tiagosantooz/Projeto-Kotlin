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


    class ViewHolderExercicio(itemExercicio: View) : RecyclerView.ViewHolder(itemExercicio){
        val textViewNome = itemExercicio.findViewById<TextView>(R.id.textViewNomeEx)
        val textViewDesc = itemExercicio.findViewById<TextView>(R.id.textViewDescEx)
        val textViewCarga = itemExercicio.findViewById<TextView>(R.id.textViewCargEx)
        val textViewRep = itemExercicio.findViewById<TextView>(R.id.textViewRepEx)
        val textViewMaquina = itemExercicio.findViewById<TextView>(R.id.textViewNomeMaqEx)

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