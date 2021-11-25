package com.example.saferoute.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.saferoute.R
import com.example.saferoute.domain.Publicacao


class PublicacaoFeedAdapter(val publicacoes: List<Publicacao>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val card = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_publication, parent, false)
        return PublicacoViewHolder(card)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val publicacao = publicacoes[position]
        if (holder is PublicacoViewHolder){
            holder.userName.text = publicacao.usuaria?.name
            holder.title.text = publicacao.titulo
            holder.textPublicatiion.text = publicacao.descricao
            holder.image.setImageBitmap(this.convertImage(publicacao.foto))
        }
    }

    fun convertImage(base64Photo: String?): Bitmap {
        val decodedString: ByteArray = Base64.decode(base64Photo, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    }

    override fun getItemCount() = publicacoes.size

    class PublicacoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val userName: TextView = itemView.findViewById(R.id.publication_username)
        val title: TextView = itemView.findViewById(R.id.publication_title)
        val textPublicatiion: TextView = itemView.findViewById(R.id.publication_text)
        val image: ImageView = itemView.findViewById(R.id.publication_image)
    }

}