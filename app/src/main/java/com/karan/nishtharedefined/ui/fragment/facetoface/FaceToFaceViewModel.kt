package com.karan.nishtharedefined.ui.fragment.facetoface

import android.app.Application
import androidx.lifecycle.*
import com.karan.nishtharedefined.model.facetoface.ModelCategory
import com.karan.nishtharedefined.model.facetoface.ModelCategoryModule
import com.karan.nishtharedefined.model.facetoface.ModelLanguage
import com.karan.nishtharedefined.networking.ServiceBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FaceToFaceViewModel(app: Application) : AndroidViewModel(app) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _categoryList = MutableLiveData<ArrayList<ModelCategory>>()
    val categoryList: LiveData<ArrayList<ModelCategory>>
        get() = _categoryList


    /**
     * fun getCategoryLiveDataList() : LiveData<ArrayList<ModelCategory>>{
    return categoryList
    }
     */

    private var _moduleList = MutableLiveData<ArrayList<ModelCategoryModule>>()
    val moduleList: LiveData<ArrayList<ModelCategoryModule>>
        get() = _moduleList

    private var _languageList = MutableLiveData<ArrayList<ModelLanguage>>()
    val languageList: LiveData<ArrayList<ModelLanguage>>
        get() = _languageList

    fun getCategoryData(lang: String) {
        uiScope.launch {
            val categoryCall = ServiceBuilder.retrofitService.getCategoryAsync(ln = lang)
            try {
                _categoryList.value = categoryCall.await()
            } catch (e: Exception) {
                _categoryList.value = ArrayList()
            }
        }
    }

    fun getCategoryModule(lang: String, categoryId: String) {
        uiScope.launch {
            val getCategoryModulesCall = ServiceBuilder.retrofitService.getModuleAsync(
                lang = lang,
                cat_id = categoryId
            )
            try {
                _moduleList.value = getCategoryModulesCall.await()
            } catch (e: Exception) {
                _moduleList.value = ArrayList()

            }
        }
    }

    fun getLanguages(modelId: Int) {
        uiScope.launch {
            val getLanguages = ServiceBuilder.retrofitService.getLanguageAsync(modelId.toString())
            try {
                _languageList.value = getLanguages.await()
            } catch (e: Exception) {
                _languageList.value = ArrayList()
            }
        }
    }


    @Suppress("UNCHECKED_CAST")
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FaceToFaceViewModel::class.java))
                return FaceToFaceViewModel(app) as T
            throw IllegalAccessException("Unable to Create View Model")
        }

    }

}

