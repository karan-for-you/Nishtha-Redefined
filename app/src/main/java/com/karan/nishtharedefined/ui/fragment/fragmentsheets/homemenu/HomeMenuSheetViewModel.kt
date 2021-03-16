package com.karan.nishtharedefined.ui.fragment.fragmentsheets.homemenu

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.karan.nishtharedefined.db.NishthaRedefinedDatabaseBuilder
import com.karan.nishtharedefined.model.nishthaonline.NishthaLanguageModel
import com.karan.nishtharedefined.ui.activity.nishthamodule.nishthalanguage.NishthaOnlineLanguageViewModel
import kotlinx.coroutines.*

class HomeMenuSheetViewModel(
    app: Application
) : AndroidViewModel(app) {

    private val nishthaRedefinedDatabase =
        NishthaRedefinedDatabaseBuilder().getDatabase(app)

    // Coroutine Scope
    private var viewModelJob = Job()
    private var uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun makeDeleteLanguages() {
        uiScope.launch {
            try {
                deleteLanguagesDB()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun makeDeleteModules(){
        uiScope.launch {
            try {
                deleteModulesDB()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun makeDeleteModuleDetails(){
        uiScope.launch {
            try {
                deleteModuleDetailsDB()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun deleteLanguagesDB() {
        withContext(Dispatchers.IO) {
            nishthaRedefinedDatabase.nishthaLanguageDao.deleteAllLanguages()
        }
    }

    private suspend fun deleteModulesDB() {
        withContext(Dispatchers.IO) {
            nishthaRedefinedDatabase.nishthaModuleDao.deleteAllModules()
        }
    }

    private suspend fun deleteModuleDetailsDB() {
        withContext(Dispatchers.IO) {
            nishthaRedefinedDatabase.nishthaModuleResourceDao.deleteAllModuleDetails()
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeMenuSheetViewModel::class.java))
                return HomeMenuSheetViewModel(app) as T
            throw IllegalAccessException("Can't create Nishtha Online View Model")
        }

    }

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }
}