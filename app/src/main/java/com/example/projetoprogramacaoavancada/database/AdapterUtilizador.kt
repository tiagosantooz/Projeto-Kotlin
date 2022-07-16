package com.example.projetoprogramacaoavancada.database

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projetoprogramacaoavancada.ListaUtilizadorFragment
import com.example.projetoprogramacaoavancada.R

class AdapterUtilizador(val fragment: ListaUtilizadorFragment) : RecyclerView.Adapter<AdapterUtilizador.ViewHolderUtilizador>() {
    var cursor: Cursor? = null
        get() = field
        set(value) {
            if(field != value){
                field = value
                notifyDataSetChanged()
            }
        }

    class ViewHolderUtilizador(itemUtilizador: View) : RecyclerView.ViewHolder(itemUtilizador) {
        val textName = itemUtilizador.findViewById<TextView>(R.id.textName)
        val textGender = itemUtilizador.findViewById<TextView>(R.id.textGender)
        val textAge = itemUtilizador.findViewById<TextView>(R.id.textAge)
        val textHeight = itemUtilizador.findViewById<TextView>(R.id.textHeight)
        val textWeight = itemUtilizador.findViewById<TextView>(R.id.textWeight)

        var utilizador : Utilizador? =  null
            get()=field
            set(value: Utilizador?) {
                field=value

                textName.text=utilizador?.nome?:""
                textGender.text=utilizador?.sexo?:""
                textAge.text=(utilizador?.idade?:"").toString()
                textHeight.text=(utilizador?.altura?:"").toString()
                textWeight.text=(utilizador?.peso?:"").toString()

            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderUtilizador {
        val itemUtilizador = fragment.layoutInflater.inflate(R.layout.item_utilizador, parent, false)
        return ViewHolderUtilizador(itemUtilizador)
    }

    override fun onBindViewHolder(holder: ViewHolderUtilizador, position: Int) {
        cursor!!.moveToPosition(position)
        holder.utilizador= Utilizador.fromCursor(cursor!!)
    }

    override fun getItemCount(): Int {
        if (cursor == null) return 0

        return cursor!!.count
    }

}