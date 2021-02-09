package com.karan.nishtharedefined.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.karan.nishtharedefined.R

class LibraryAdapter(
    var listOfPairs: ArrayList<Pair<String?, String?>>,
    var context: Context
) : RecyclerView.Adapter<LibraryAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(
                context
            ).inflate(
                R.layout.libarary_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvLibraryItem.text = listOfPairs[position].first
    }

    override fun getItemCount(): Int {
        return listOfPairs.size
    }

    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var tvLibraryItem : TextView = view.findViewById(R.id.tvLibraryItem)
    }
}