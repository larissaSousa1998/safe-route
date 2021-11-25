package com.example.saferoute.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.saferoute.R
import com.example.saferoute.domain.GroupoLocomocaoUsuaria


class GruposLocomocaoInAdapter(
    val grupos: List<GroupoLocomocaoUsuaria>,
    val listener: OnItemClickListener
): RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val card = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_group_locomotion_in, parent, false)
        return GruposViewHolder(card)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = grupos[position]
        if(holder is GruposViewHolder){
            holder.title.text = currentItem.title
            holder.origem.text = currentItem.viagem.nomeOrigem
            holder.destino.text = currentItem.viagem.nomeDestino
            holder.adm.text = currentItem.nome
        }
    }

    override fun getItemCount(): Int {
        return grupos.size
    }

    inner class GruposViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),
        View.OnClickListener{
        val title: TextView = itemView.findViewById(R.id.txv_title_group_in)
        val origem: TextView = itemView.findViewById(R.id.txv_origim_group_in)

        val destino: TextView = itemView.findViewById(R.id.txv_destiny_group_in)
        val adm: TextView = itemView.findViewById(R.id.txv_name_adm_group_in)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
}