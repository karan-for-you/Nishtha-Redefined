package com.karan.nishtharedefined.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.model.HomeMenu

class HomeAdapter(
    private var listOfItems : ArrayList<HomeMenu>,
    private var context : Context,
    private var onHomeMenuClickedListener: OnHomeMenuClickListener
) : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.home_gridview,
            parent,
            false
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(listOfItems[position].img).into(holder.ivHomeIcon)
        holder.tvHomeText.text = listOfItems[position].name
        holder.itemView.setOnClickListener {
            onHomeMenuClickedListener.onHomeMenuClicked(listOfItems[position].id)
        }
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }

    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var ivHomeIcon : ImageView = view.findViewById(R.id.ivHomeIcon)
        var tvHomeText : TextView = view.findViewById(R.id.tvHomeItem)
    }

    interface OnHomeMenuClickListener{
        fun onHomeMenuClicked(id : Int)
    }

}