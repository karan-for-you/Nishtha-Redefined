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
import com.karan.nishtharedefined.model.home.MenuData

class HomeBottomMenuAdapter(
    private var context: Context,
    private var listItems : ArrayList<MenuData>,
    private var onHomeBottomMenuClickListener: OnHomeBottomMenuClickListener
) : RecyclerView.Adapter<HomeBottomMenuAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(context).inflate(
            R.layout.home_bottom_menu_view,
            parent,
            false
        )
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvLabel.text = listItems[position].name
        Glide.with(context).load(listItems[position].img).into(holder.ivMenuIcon)
        if(position == 1)
            holder.ivChevron.visibility = View.VISIBLE
        else
            holder.ivChevron.visibility = View.GONE
        holder.itemView.setOnClickListener {
            onHomeBottomMenuClickListener.onHomeBottomMenuClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var ivMenuIcon : ImageView = view.findViewById(R.id.ivMenuIcon)
        var tvLabel : TextView = view.findViewById(R.id.tvLabel)
        var ivChevron : ImageView = view.findViewById(R.id.ivChevron)
    }

    interface OnHomeBottomMenuClickListener{
        fun onHomeBottomMenuClicked(position: Int)
    }


}