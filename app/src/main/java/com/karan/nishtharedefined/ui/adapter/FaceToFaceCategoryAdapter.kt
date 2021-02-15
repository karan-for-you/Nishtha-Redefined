package com.karan.nishtharedefined.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.model.ModelCategory
import java.util.*

class FaceToFaceCategoryAdapter(
    var context : Context,
    var listOfModules : ArrayList<ModelCategory>,
    var faceToFaceCategoryListener: FaceToFaceCategoryListener
): RecyclerView.Adapter<FaceToFaceCategoryAdapter.MyViewHolder>() {

    private var selectedPosition : Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.face_to_face_category,
            parent,
            false
        )
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvCategoryName.text = listOfModules[position].cat_name
        if(selectedPosition == position) {
            holder.tvCategoryName.setBackgroundResource(R.drawable.bordered_filled_face_to_face_category)
            //holder.tvCategoryName.setTextColor(Color.WHITE)
        }
        else {
            holder.tvCategoryName.setBackgroundResource(R.drawable.bordered_unfilled_face_to_face_category)
            //holder.tvCategoryName.setTextColor(Color.BLACK)
        }
        holder.itemView.setOnClickListener {
            faceToFaceCategoryListener.onFaceToFaceCategoryClicked(listOfModules[position].id.toInt())
            selectedPosition = position
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return listOfModules.size
    }

    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val tvCategoryName : TextView = view.findViewById(R.id.tvCategoryName)
    }

    interface FaceToFaceCategoryListener{
        fun onFaceToFaceCategoryClicked(position: Int)
    }

}