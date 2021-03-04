package com.karan.nishtharedefined.ui.activity.nishthamodule

import android.app.Application
import androidx.lifecycle.*
import com.karan.nishtharedefined.db.NishthaRedefinedDatabaseBuilder
import com.karan.nishtharedefined.db.dataobjects.NishthaOnlineLanguage
import com.karan.nishtharedefined.model.nishthaonline.NishthaLanguageModel
import com.karan.nishtharedefined.model.nishthaonline.NishthaModuleModel
import com.karan.nishtharedefined.networking.ServiceBuilder
import kotlinx.coroutines.*

class NishthaOnlineLanguageViewModel(
        app: Application
) : AndroidViewModel(app) {


    private val nishthaRedefinedDatabase =
            NishthaRedefinedDatabaseBuilder().getDatabase(app)

    // Coroutine Scope
    private var viewModelJob = Job()
    private var uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    // LiveData Objects
    private var _nishthaLanguagesList = MutableLiveData<ArrayList<NishthaLanguageModel>>()
    val nishthaLanguageList: LiveData<ArrayList<NishthaLanguageModel>>
        get() = _nishthaLanguagesList

    private var _nishthaResourceList = MutableLiveData<ArrayList<NishthaModuleModel>>()
    val nishthaResourceList: LiveData<ArrayList<NishthaModuleModel>>
        get() = _nishthaResourceList


    // Network Calls
    fun getNishthaOnlineLanguages() {
        uiScope.launch {
            val service = ServiceBuilder.retrofitService.getNishthaOnlineLanguageAsync()
            try {
                _nishthaLanguagesList.value = service.await()
            } catch (e: Exception) {
                _nishthaLanguagesList.value = ArrayList()
            }
        }
    }

    fun getNishthaOnlineModuleByLanguage(lang: String?) {
        uiScope.launch {
            val service = ServiceBuilder.retrofitService.getOnlineResourceAsync(lang = lang!!)
            try {
                _nishthaResourceList.value = service.await()
            } catch (e: java.lang.Exception) {
                _nishthaResourceList.value = ArrayList()
            }
        }
    }

    // Database - Room Calls
    fun makeInsertLanguageDBCall(t: ArrayList<NishthaLanguageModel>) {
        uiScope.launch {
            try {
                insertLanguages(t)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    fun makeSelectAllLanguagesCall() {
        uiScope.launch {
            try {
                getLanguages()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun insertLanguages(t: ArrayList<NishthaLanguageModel>) {
        val modifiedArrayList = ArrayList<NishthaOnlineLanguage>()
        for (model in t)
            modifiedArrayList.add(NishthaOnlineLanguage(
                    model.langCode!!,
                    model.langText!!,
                    model.langName!!)
            )
        withContext(Dispatchers.IO) {
            nishthaRedefinedDatabase.nishthaLanguageDao.insertAllLanguages(modifiedArrayList)
        }
    }

    private suspend fun getLanguages() {
        withContext(Dispatchers.IO) {
            nishthaRedefinedDatabase.nishthaLanguageDao.getLanguages()
        }
        // Update the value of LiveData here
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NishthaOnlineLanguageViewModel::class.java))
                return NishthaOnlineLanguageViewModel(app) as T
            throw IllegalAccessException("Can't create Nishtha Online View Model")
        }

    }

}