package com.karan.nishtharedefined.ui.dialog.contactdebug

import android.app.Application
import androidx.lifecycle.*
import com.karan.nishtharedefined.db.Contact
import com.karan.nishtharedefined.db.NishthaRedefinedDatabaseBuilder
import com.karan.nishtharedefined.utils.Logger
import kotlinx.coroutines.*

class ContactsDebugViewModel(
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val databaseObject = NishthaRedefinedDatabaseBuilder().getDatabase(application)

    private var _numberOfContacts = MutableLiveData<Int>()
    val numberOfContacts: LiveData<Int>
        get() = _numberOfContacts

    private var _primaryKeyReturned = MutableLiveData<Long>()
    val primaryKeyReturned: LiveData<Long>
        get() = _primaryKeyReturned

    fun getContactsSize() {
        uiScope.launch {
            try {
                getNumberOfContactsAndSetValue()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getContactSizeOnly(){
        uiScope.launch {
            try {
                getNumberOfContactsOnly()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun postContact(contact: Contact) {
        uiScope.launch {
            try {
                makeInsertContactDBCall(contact = contact)
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteContacts() {
        uiScope.launch {
            try {
                deleteCall()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun getNumberOfContactsAndSetValue() {
        val contactListData: List<Contact>
        withContext(Dispatchers.IO) {
            contactListData = databaseObject.contactsDao.getContacts()
            Logger.logDebug(
                tag = "No of Contacts",
                message = "" + contactListData.size
            )
        }
        Logger.logDebug(
                tag = "Outside of the Coroutine Context",
                message = "Tweet me"
        )
        _numberOfContacts.value = contactListData.size
    }

    private suspend fun getNumberOfContactsOnly() {
        val contactListData: List<Contact>
        withContext(Dispatchers.IO) {
            contactListData = databaseObject.contactsDao.getContacts()
            Logger.logDebug(
                    tag = "No of Contacts",
                    message = "" + contactListData.size
            )
            for(c in contactListData){
                Logger.logDebug(
                        tag = "Contact",
                        message = "" + c.contactName+" "+c.id+" "+c.contactNumber
                )
            }
        }

    }


    private suspend fun makeInsertContactDBCall(contact: Contact) {
        val primaryKey: Long
        withContext(Dispatchers.IO) {
             primaryKey = databaseObject.contactsDao.insertContact(contact = contact)
        }
        // Do not update value of LiveData in Coroutine Context since
        // it's a background thread still running
        //  java.lang.IllegalStateException: Cannot invoke setValue on a background thread
        _primaryKeyReturned.value = primaryKey
    }

    private suspend fun deleteCall() {
        withContext(Dispatchers.IO) {
            databaseObject.contactsDao.deleteAllContacts()
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ContactsDebugViewModel::class.java))
                return ContactsDebugViewModel(app) as T
            throw IllegalAccessException("Unable to Create Contact Debug View Model")
        }

    }
}