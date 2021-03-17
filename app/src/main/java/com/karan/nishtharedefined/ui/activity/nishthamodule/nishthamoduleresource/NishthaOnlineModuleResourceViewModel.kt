package com.karan.nishtharedefined.ui.activity.nishthamodule.nishthamoduleresource

import android.app.Application
import androidx.lifecycle.*
import com.karan.nishtharedefined.db.NishthaRedefinedDatabaseBuilder
import com.karan.nishtharedefined.db.dataobjects.NishthaModule
import com.karan.nishtharedefined.db.dataobjects.NishthaModuleResource
import com.karan.nishtharedefined.model.nishthaonline.NishthaOnlineModuleResourceModel
import com.karan.nishtharedefined.networking.ServiceBuilder
import kotlinx.coroutines.*

class NishthaOnlineModuleResourceViewModel(app: Application) : AndroidViewModel(app) {


    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val nishthaRedefinedDatabase =
        NishthaRedefinedDatabaseBuilder().getDatabase(app)

    // LiveData Object(s) - API
    private var _moduleResourceList = MutableLiveData<ArrayList<NishthaOnlineModuleResourceModel>>()
    val moduleResourceList: LiveData<ArrayList<NishthaOnlineModuleResourceModel>>
        get() = _moduleResourceList

    // LiveData Object(s) - Room Database
    private var _moduleResourceListRoom = MutableLiveData<ArrayList<NishthaModuleResource>>()
    val moduleResourceListRoom: LiveData<ArrayList<NishthaModuleResource>>
        get() = _moduleResourceListRoom

    private var _insertedIds = MutableLiveData<Array<Long>>()
    val insertedIds: LiveData<Array<Long>>
        get() = _insertedIds

    fun getModuleResources(lang: String?, modId: String?) {
        val service = ServiceBuilder.retrofitService.getOnlineResourceDetailAsync(
            lang = lang,
            modId = modId
        )
        uiScope.launch {
            try {
                _moduleResourceList.value = service.await()
            } catch (e: Exception) {
                _moduleResourceList.value = ArrayList()
                e.printStackTrace()
            }
        }
    }

    // Pre Database - Room Calls
    fun makeInsertResourcesCall(
        t: ArrayList<NishthaOnlineModuleResourceModel>,
        module: String,
        lang: String
    ) {
        uiScope.launch {
            try {
                insertResources(t, module, lang)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun makeSelectResourcesCall(lang: String, moduleId: String) {
        uiScope.launch {
            try {

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun insertResources(
        t: ArrayList<NishthaOnlineModuleResourceModel>,
        module: String,
        lang: String
    ) {
        withContext(Dispatchers.IO) {
            val modifiedArrayList = ArrayList<NishthaModuleResource>()
            var insertedIds: Array<Long>
            for (model in t)
                modifiedArrayList.add(
                    NishthaModuleResource(
                        resource__name = model.resource__name,
                        resource__link = model.resource__link,
                        resource__type = model.resource__type,
                        resource__html = model.resource__html,
                        module = module,
                        lang = lang
                    )
                )
            nishthaRedefinedDatabase.nishthaModuleResourceDao.insertAllModuleDetails(
                modifiedArrayList
            )
        }
    }

    private suspend fun selectResources(lang: String, moduleId: String) {
        withContext(Dispatchers.IO) {
            nishthaRedefinedDatabase.nishthaModuleResourceDao.getModuleDetails(

            )
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