package com.example.rickandmortyapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class ListAdapter(private val dataSet: ArrayList<GetCharacterByIdResponse>,context:Context):RecyclerView.Adapter<ListAdapter.ViewHolder>(){
    private val mContext: Context = context
//    private val myDataSet: ArrayList<GetCharacterByIdResponse> = dataSet

//    fun addItem(item: GetCharacterByIdResponse) {
//        myDataSet.add(item)
//}
//    private val onClickListener: OnItemClickListener? = null

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

//        holder.cardSpecies.text = myDataSet[position].species
//        holder.cardName.text = myDataSet[position].name
//        holder.cardGender.text = myDataSet[position].gender
//        Picasso.get().load(myDataSet[position].image).into(holder.cardPhoto)
//        holder.cardSpecies.text = myDataSet[position].species


        holder.cardSpecies.text = dataSet[position].species
        holder.cardName.text = dataSet[position].name
        holder.cardGender.text = dataSet[position].gender
        Picasso.get().load(dataSet[position].image).into(holder.cardPhoto)
        holder.cardSpecies.text = dataSet[position].species
        holder.itemView.setOnClickListener {
            val extras = Bundle()
            val intent = Intent(holder.itemView.context, MainActivity2::class.java)
            extras.putInt("index", dataSet[position].id)
//            extras.putInt("index", myDataSet[position].id)
            intent.putExtras(extras);
            holder.itemView.context.startActivity(intent);
        }
    }

    override fun getItemCount(): Int {
//        return  myDataSet.size
        return  dataSet.size
    }
}
