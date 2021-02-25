package com.karan.nishtharedefined.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.model.nishthaonline.NishthaModuleModel

class ModuleAdapter(
    var context: Context,
    var listOfModules: ArrayList<NishthaModuleModel>,
    var onModuleClickedListener: OnModuleClickedListener
) : RecyclerView.Adapter<ModuleAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(
                context
            ).inflate(
                R.layout.online_module_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvModuleName.text = listOfModules[position].modName
        holder.tvPos.text = (position+1).toString()
        holder.itemView.setOnClickListener {
            onModuleClickedListener.onModuleClicked(listOfModules[position])
        }
    }

    override fun getItemCount(): Int {
        return listOfModules.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvModuleName = view.findViewById(R.id.tvModuleName) as TextView
        var tvPos = view.findViewById(R.id.tvPos) as TextView
    }

    interface OnModuleClickedListener{
        fun onModuleClicked(module : NishthaModuleModel)
    }

}