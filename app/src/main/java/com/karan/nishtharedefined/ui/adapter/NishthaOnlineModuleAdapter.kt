package com.karan.nishtharedefined.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.karan.nishtharedefined.R

class NishthaOnlineModuleAdapter(
    var context : Context,
    var listOfModules : ArrayList<com.karan.nishtharedefined.model.nishthaonline.NishthaModuleModel>,
    var onModuleClickListener: OnModuleResourceClickListener
) : RecyclerView.Adapter<NishthaOnlineModuleAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(context).inflate(
            R.layout.list_module_view,
            parent,
            false
        )
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvModuleText.text = listOfModules[position].modName
        holder.itemView.setOnClickListener {
            onModuleClickListener.onModuleClicked(listOfModules[position])
        }
    }

    fun addAllItems(listOfModules : ArrayList<com.karan.nishtharedefined.model.nishthaonline.NishthaModuleModel>){
        listOfModules.addAll(listOfModules)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listOfModules.size
    }

    class MyViewHolder(
        view : View
    ) : RecyclerView.ViewHolder(view){
        val tvModuleText: TextView = view.findViewById(R.id.tvModuleText)
    }

    interface OnModuleResourceClickListener{
        fun onModuleClicked(nishthaOnlineModule: com.karan.nishtharedefined.model.nishthaonline.NishthaModuleModel)
    }


}