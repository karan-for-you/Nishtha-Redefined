package com.karan.nishtharedefined.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.model.facetoface.ModelLanguage

class ModuleLanguageAdapter(
    var context : Context,
    var languageList : ArrayList<ModelLanguage>,
    var onLanguageSelectedListener: OnLanguageSelectedListener
    ) : RecyclerView.Adapter<ModuleLanguageAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(context).inflate(
            R.layout.language_row,
            parent,
            false
        )
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvLanguage.text = languageList[position].avail_lang_list
        holder.itemView.setOnClickListener {
            onLanguageSelectedListener.onLanguageSelected(
                languageList[position].avail_lang_list,
                languageList[position].id
            )
        }
    }

    override fun getItemCount(): Int {
        return languageList.size
    }

    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val tvLanguage : TextView = view.findViewById(R.id.tvLanguage)
    }

    interface OnLanguageSelectedListener{
        fun onLanguageSelected(language : String, modId : String)
    }


}