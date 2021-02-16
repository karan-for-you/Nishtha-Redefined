package com.karan.nishtharedefined.ui.fragment.home

import android.app.Application
import androidx.lifecycle.*
import com.karan.nishtharedefined.model.home.HomeMenu
import com.karan.nishtharedefined.utils.DataGenerator

class HomeViewModel(var app : Application) : AndroidViewModel(app) {

    private val _homeMenuList = MutableLiveData<ArrayList<HomeMenu>>()
    val homeMenuList: LiveData<ArrayList<HomeMenu>>
        get() =_homeMenuList

    fun prepareHomeMenuData(){
        _homeMenuList.value= DataGenerator.prepareHomeMenuData(app)
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