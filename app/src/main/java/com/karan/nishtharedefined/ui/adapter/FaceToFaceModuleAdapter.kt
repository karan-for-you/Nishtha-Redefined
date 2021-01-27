package com.karan.nishtharedefined.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.model.ModelCategoryModule
import java.util.ArrayList

class FaceToFaceModuleAdapter(
    var context : Context,
    var listOfModules : ArrayList<ModelCategoryModule>,
    var faceToFaceModuleListener: OnFaceToFaceModuleClickListener
) : RecyclerView.Adapter<FaceToFaceModuleAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.face_to_face_module_view,parent,false
        )
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvModuleName.text = listOfModules[position].mod_name
        holder.itemView.setOnClickListener { faceToFaceModuleListener.onFaceToFaceModuleClicked(position) }
    }

    override fun getItemCount(): Int {
        return listOfModules.size
    }

    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val tvModuleName : TextView = view.findViewById(R.id.tvModuleText)
    }

    interface OnFaceToFaceModuleClickListener{
        fun onFaceToFaceModuleClicked(position: Int)
    }


}