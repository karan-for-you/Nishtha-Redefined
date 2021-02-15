package com.karan.nishtharedefined.ui.activity.nishthamodule

import android.app.Application
import androidx.lifecycle.*
import com.karan.nishtharedefined.model.NishthaOnlineModuleDetail
import com.karan.nishtharedefined.networking.ServiceBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NishthaOnlineModuleViewModel(app:Application) :AndroidViewModel(app) {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _nishthaOnlineModuleList = MutableLiveData<ArrayList<NishthaOnlineModuleDetail>>()
    val nishthaOnlineModuleList : LiveData<ArrayList<NishthaOnlineModuleDetail>>
        get() = _nishthaOnlineModuleList

    fun getNishthaOnlineModuleList(lang : String,catId : String){
        uiScope.launch {
            val service = ServiceBuilder.retrofitService.getOnlineResourceDetailAsync(
                lang = lang,
                cat_id = catId
            )
            try {
                _nishthaOnlineModuleList.value = service.await()
            }catch (e : Exception){
                _nishthaOnlineModuleList.value = ArrayList()
            }
        }

    }


    @Suppress("UNCHECKED_CAST")
    class Factory(val app : Application) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(NishthaOnlineModuleViewModel::class.java))
                return NishthaOnlineModuleViewModel(app) as T
            throw IllegalAccessException("Can't create Download View Model")
        }
    }
}