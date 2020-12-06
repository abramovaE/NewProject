package com.abramovae.newproject

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ActorsViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val name: TextView = itemView.findViewById(R.id.nameActor)
    private val img: ImageView = itemView.findViewById(R.id.imgActor)

    fun bind(actor: Actor){
        name.setText(actor.name)
        img.setImageResource(actor.imgId)


    }
}