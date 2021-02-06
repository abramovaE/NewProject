package com.abramovae.newproject.view

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abramovae.newproject.BuildConfig
import com.abramovae.newproject.R
import com.abramovae.newproject.data.RetrofitModule
import com.android.academy.fundamentals.homework.features.data.Actor
import com.bumptech.glide.Glide

class ActorsViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val name: TextView = itemView.findViewById(R.id.nameActor)
    private val img: ImageView = itemView.findViewById(R.id.imgActor)
    val BASE_URL = "https://image.tmdb.org/t/p/w500"

    fun bind(actor: Actor){
        name.setText(actor.name)
        Glide.with(itemView.context).load(Uri.parse( BASE_URL + actor.picture)).into(img)
    }
}