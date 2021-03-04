package com.karan.nishtharedefined.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.model.nishthaonline.NishthaLanguageModel

class NishthaOnlineLanguageAdapter(
    var languageList : ArrayList<NishthaLanguageModel>,
    var context : Context
) :
    RecyclerView.Adapter<NishthaOnlineLanguageAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(context).inflate(
            R.layout.grid_language_view,
            parent,
            false
        )
        return MyViewHolder(v)
    }

    fun addAllItems(languageList: ArrayList<NishthaLanguageModel>){
        languageList.addAll(languageList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvLanguage.text = languageList[position].langName
    }

    override fun getItemCount(): Int {
        return languageList.size
    }

    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val tvLanguage : TextView = view.findViewById(R.id.tvLanguage)
    }

    interface OnLanguageSelectedListener {
        fun onLanguageSelected(languageModel: NishthaLanguageModel)
    }

}