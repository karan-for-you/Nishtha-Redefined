package com.karan.nishtharedefined.ui.activity.facetoface

import android.app.Application
import androidx.lifecycle.*
import com.karan.nishtharedefined.model.facetoface.ModelResourceType
import com.karan.nishtharedefined.networking.ServiceBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class FaceToFaceResourceViewModel(app : Application) : AndroidViewModel(app) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _resourceList = MutableLiveData<ArrayList<ModelResourceType>>()
    val resourceList : LiveData<ArrayList<ModelResourceType>>
        get() = _resourceList

    fun getResources(lang: String, modelId: String){
        uiScope.launch {
            val getResources = ServiceBuilder.retrofitService.getResourceTypeAsync(
                lang = lang,
                mod_id = modelId
            )
            try{
                _resourceList.value = getResources.await()
            }catch (e : java.lang.Exception){
                _resourceList.value = ArrayList()
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(val app : Application) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(FaceToFaceResourceViewModel::class.java))
                return FaceToFaceResourceViewModel(app) as T
            throw IllegalAccessException("Can't create Face To Face Resource View Model")
        }

    }
}