package com.karan.nishtharedefined.ui.activity.nishthamodule.nishthamoduleresource

import android.app.Application
import androidx.lifecycle.*
import com.karan.nishtharedefined.model.nishthaonline.NishthaOnlineModuleResourceModel
import com.karan.nishtharedefined.networking.ServiceBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NishthaOnlineModuleResourceViewModel(app : Application) : AndroidViewModel(app) {


    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _moduleResourceList = MutableLiveData<ArrayList<NishthaOnlineModuleResourceModel>>()
    val moduleResourceList : LiveData<ArrayList<NishthaOnlineModuleResourceModel>>
        get() = _moduleResourceList

    fun getModuleResources(lang:String, modId : String){
        val service = ServiceBuilder.retrofitService.getOnlineResourceDetailAsync(
            lang = lang,
            modId = modId
        )
        uiScope.launch {
            try{
                _moduleResourceList.value = service.await()
            }catch (e : Exception){
                _moduleResourceList.value = ArrayList()
                e.printStackTrace()
            }
        }
    }


    @Suppress("UNCHECKED_CAST")
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NishthaOnlineModuleResourceViewModel::class.java))
                return NishthaOnlineModuleResourceViewModel(app) as T
            throw IllegalAccessException("Can't create Nishtha Online Module Resource View Model")
        }

    }

}