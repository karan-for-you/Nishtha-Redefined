package com.karan.nishtharedefined.ui.dialog.contactdebug

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.karan.nishtharedefined.database.NishthaRedefinedDatabaseBuilder
import kotlinx.coroutines.*

class ContactDebugDialogViewModel(
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val databaseObject = NishthaRedefinedDatabaseBuilder().getDatabase(application)

    fun getContactsSize(){
        uiScope.launch {
            try {
                getNumberOfContacts()
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
    private suspend fun getNumberOfContacts(){
        withContext(Dispatchers.IO){
            val contactListData = databaseObject.contactDao.getContacts()
            com.karan.nishtharedefined.utils.Logger.logDebug(tag = "No of Contacts",
                message = ""+contactListData.value?.size)
        }
    }


    private suspend fun makeInsertContactDBCall(){
        withContext(Dispatchers.IO){

        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(val app : Application) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(ContactDebugDialogViewModel::class.java))
                return ContactDebugDialogViewModel(app) as T
            throw IllegalAccessException("Unable to Create Contact Debug View Model")
        }

    }
}