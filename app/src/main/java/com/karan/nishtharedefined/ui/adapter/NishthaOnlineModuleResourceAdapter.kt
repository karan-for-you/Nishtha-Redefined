package com.karan.nishtharedefined.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.karan.nishtharedefined.R

class NishthaOnlineModuleResourceAdapter(
    var context : Context,
    var listOfModuleResources : ArrayList<com.karan.nishtharedefined.model.nishthaonline.NishthaOnlineModuleResourceModel>,
    var onModuleClickListener: OnModuleResourceClickListener
) : RecyclerView.Adapter<NishthaOnlineModuleResourceAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(context).inflate(
            R.layout.resources_gridview,parent,false
        )
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvModuleResourceText.text = listOfModuleResources[position].resource__name
        holder.itemView.setOnClickListener {
            onModuleClickListener.onModuleResourceClicked()
        }
    }

    override fun getItemCount(): Int {
        return listOfModuleResources.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var tvModuleResourceText: TextView = view.findViewById(R.id.tvModuleResourceText)
    }

    interface OnModuleResourceClickListener{
        fun onModuleResourceClicked()
    }

}