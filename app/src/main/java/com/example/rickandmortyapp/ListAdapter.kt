package com.example.rickandmortyapp

import androidx.recyclerview.widget.RecyclerView
class ListAdapter(private val dataSet:Array<GetCharacterByIdResponse>):RecyclerView.Adapter<ListAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.textView)
        }
}