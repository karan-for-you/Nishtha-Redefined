package com.karan.nishtharedefined.ui.activity.nishthamodule.nishthamodule

import android.app.Application
import androidx.lifecycle.*
import com.karan.nishtharedefined.db.NishthaRedefinedDatabaseBuilder
import com.karan.nishtharedefined.db.dataobjects.NishthaModule
import com.karan.nishtharedefined.model.nishthaonline.NishthaModuleModel
import com.karan.nishtharedefined.model.nishthaonline.NishthaOnlineModuleResourceModel
import com.karan.nishtharedefined.networking.ServiceBuilder
import kotlinx.coroutines.*

class NishthaOnlineModuleViewModel(app:Application) :AndroidViewModel(app) {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val nishthaRedefinedDatabase =
        NishthaRedefinedDatabaseBuilder().getDatabase(app)

    private var _nishthaResourceList = MutableLiveData<ArrayList<NishthaModuleModel>>()
    val nishthaResourceList: LiveData<ArrayList<NishthaModuleModel>>
        get() = _nishthaResourceList

    private var _nishthaModulesListRoom = MutableLiveData<ArrayList<NishthaModule>>()
    val nishthaModulesListRoom: LiveData<ArrayList<NishthaModule>>
        get() = _nishthaModulesListRoom

    fun getNishthaOnlineModuleByLanguage(lang: String?) {
        uiScope.launch {
            val service = ServiceBuilder.retrofitService.getOnlineResourceAsync(
                lang = lang!!
            )
            try {
                _nishthaResourceList.value = service.await()
            } catch (e: java.lang.Exception) {
                _nishthaResourceList.value = ArrayList()
            }
        }
    }

    // Pre Room Calls - DB
    fun makeInsertModulesDBCall(t : ArrayList<NishthaModuleModel>){
        uiScope.launch {
            try {
                insertModules(t)
            }catch (e : Exception){
                e.printStackTrace()
            }
        }
    }

    fun makeSelectAllModulesDBCall(langCode : String?){
        uiScope.launch {
            try {
                getModules(langCode = langCode)
            }catch (e : Exception){
                e.printStackTrace()
            }
        }
    }

    // Suspend Calls
    private suspend fun insertModules(t : ArrayList<NishthaModuleModel>){
        val modifiedArrayList = ArrayList<NishthaModule>()
        for(model in t)
            modifiedArrayList.add(
                NishthaModule(
                    modId = model.modId!!,
                    modLang = model.modLang!!,
                    modName = model.modName!!
                )
            )
        withContext(Dispatchers.IO){
            nishthaRedefinedDatabase.nishthaModuleDao.insertAllModules(modifiedArrayList)
        }
    }

    private suspend fun getModules(langCode : String?) {
        var nishthaModulesList: ArrayList<NishthaModule>
        withContext(Dispatchers.IO) {
            nishthaModulesList =
                ArrayList(nishthaRedefinedDatabase.nishthaModuleDao.getModules(langCode))
        }
        // Update the value of LiveData here
        _nishthaModulesListRoom.value = nishthaModulesList
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