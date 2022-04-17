package com.example.rickandmortyapp

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.net.URL

class ListAdapter(private val dataSet: ArrayList<GetCharacterByIdResponse>):RecyclerView.Adapter<ListAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardName: TextView = view.findViewById(R.id.cardName)
        val cardSpecies: TextView = view.findViewById(R.id.cardSpecies)
        val cardGender: TextView = view.findViewById(R.id.cardGender)
        val cardPhoto: ImageView = view.findViewById(R.id.cardPhoto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cardSpecies.text = dataSet[position].species
        holder.cardName.text = dataSet[position].name
        holder.cardGender.text = dataSet[position].gender
        Picasso.get().load(dataSet[position].image).into(holder.cardPhoto)
//        holder.cardPhoto.setImageBitmap(BitmapFactory.decodeStream(URL(dataSet[position].image).openStream()))
        holder.cardSpecies.text = dataSet[position].species
    }

    override fun getItemCount(): Int {
        return  dataSet.size
    }
}