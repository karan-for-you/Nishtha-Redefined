package com.karan.nishtharedefined.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.model.NishthaOnlineModuleDetail

class NishthaOnlineModuleAdapter(
    var context : Context,
    var listOfResources : ArrayList<NishthaOnlineModuleDetail>
) : RecyclerView.Adapter<NishthaOnlineModuleAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(context).inflate(
            R.layout.nishtha_online_module_view,
            parent,
            false
        )
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvResourceName.text = listOfResources[position].resource__name
    }

    override fun getItemCount(): Int {
        return listOfResources.size
    }

    class MyViewHolder(
        view : View
    ) : RecyclerView.ViewHolder(view){
        val ivModuleIcon = view.findViewById<ImageView>(R.id.ivModuleIcon)
        val tvResourceName = view.findViewById<TextView>(R.id.tvResourceName)
    }


}