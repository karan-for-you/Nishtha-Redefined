package com.karan.nishtharedefined.ui.activity.nishthamodule.nishthalanguage

import android.app.Application
import androidx.lifecycle.*
import com.karan.nishtharedefined.db.NishthaRedefinedDatabaseBuilder
import com.karan.nishtharedefined.db.dataobjects.NishthaOnlineLanguage
import com.karan.nishtharedefined.model.nishthaonline.NishthaLanguageModel
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

    // LiveData Object(s) - API
    private var _nishthaLanguagesList = MutableLiveData<ArrayList<NishthaLanguageModel>>()
    val nishthaLanguageList: LiveData<ArrayList<NishthaLanguageModel>>
        get() = _nishthaLanguagesList

    // LiveData Object(s) - Room
    private var _nishthaLanguagesListRoom = MutableLiveData<ArrayList<NishthaOnlineLanguage>>()
    val nishthaLanguageListRoom: LiveData<ArrayList<NishthaOnlineLanguage>>
        get() = _nishthaLanguagesListRoom

    private var _insertedIds = MutableLiveData<Array<Long>>()
    val insertedIds : LiveData<Array<Long>>
        get() = _insertedIds

    // Network Call(s)
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

    // Database - Pre Room Calls
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

    // Database - Suspend Calls
    private suspend fun insertLanguages(t: ArrayList<NishthaLanguageModel>) {
        val modifiedArrayList = ArrayList<NishthaOnlineLanguage>()
        var insertedIds : Array<Long>
        for (model in t)
            modifiedArrayList.add(
                NishthaOnlineLanguage(
                    model.langCode!!,
                    model.langText!!,
                    model.langName!!
                )
            )
        withContext(Dispatchers.IO) {
            insertedIds = nishthaRedefinedDatabase.nishthaLanguageDao.insertAllLanguages(modifiedArrayList)
        }
        _insertedIds.value = insertedIds
    }

    private suspend fun getLanguages() {
        var nishthaLanguageList: ArrayList<NishthaOnlineLanguage>
        withContext(Dispatchers.IO) {
            nishthaLanguageList =
                ArrayList(nishthaRedefinedDatabase.nishthaLanguageDao.getLanguages())
        }
        // Update the value of LiveData here
        _nishthaLanguagesListRoom.value = nishthaLanguageList
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NishthaOnlineLanguageViewModel::class.java))
                return NishthaOnlineLanguageViewModel(app) as T
            throw IllegalAccessException("Can't create Nishtha Online View Model")
        }

    }

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }

}