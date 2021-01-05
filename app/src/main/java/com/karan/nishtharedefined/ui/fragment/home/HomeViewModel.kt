package com.karan.nishtharedefined.ui.fragment.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.karan.nishtharedefined.utils.DataGenerator

class HomeViewModel(var app : Application) : AndroidViewModel(app) {

    private fun prepareHomeMenuData(){
        DataGenerator.prepareHomeMenuData(app)
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(val app : Application) :ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(HomeViewModel::class.java))
                return HomeViewModel(app) as T
            throw IllegalAccessException("Unable to Create View Model")
        }

    }

}