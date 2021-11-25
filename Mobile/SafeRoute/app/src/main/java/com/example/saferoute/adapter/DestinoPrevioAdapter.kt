package com.example.saferoute.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.saferoute.R
import com.example.saferoute.domain.DestinoPrevio

class DestinoPrevioAdapter(
    val destinos: List<DestinoPrevio>,
    val listener: OnItemClickListener
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val card = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_destination, parent, false)
        return DestinoPrevioViewHolder(card)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val destinos = destinos[position]
        if (holder is DestinoPrevioViewHolder){
            holder.title.text = destinos.descricao
            holder.origem.text = destinos.origem
            holder.destino.text = destinos.logradouro
        }
    }

    override fun getItemCount() = destinos.size

    inner class DestinoPrevioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val title: TextView = itemView.findViewById(R.id.txv_title_previous_destinations)
        val origem: TextView = itemView.findViewById(R.id.txv_origim_previous_destinations)
        val destino: TextView = itemView.findViewById(R.id.txv_destiny_previous_destionations)

        init {
            itemView.setOnClickListener(this)
        }

            override fun onClick(p0: View?) {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position)
                }
            }
        }

        interface OnItemClickListener{
            fun onItemClick(position: Int)
        }
    }
