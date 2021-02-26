package com.karan.nishtharedefined.ui.dialog.contactdebug

import android.app.Application
import androidx.lifecycle.*
import com.karan.nishtharedefined.db.Contact
import com.karan.nishtharedefined.db.NishthaRedefinedDatabaseBuilder
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

    private var _primaryKeyReturned = MutableLiveData<Int>()
    val primaryKeyReturned: LiveData<Int>
        get() = _primaryKeyReturned

    fun getContactsSize() {
        uiScope.launch {
            try {
                getNumberOfContacts()
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

    private suspend fun getNumberOfContacts() {
        val contactListData: List<Contact>
        withContext(Dispatchers.IO) {
            contactListData = databaseObject.contactsDao.getContacts()
            com.karan.nishtharedefined.utils.Logger.logDebug(
                tag = "No of Contacts",
                message = "" + contactListData.size
            )
        }
        _numberOfContacts.value = contactListData.size
    }


    private suspend fun makeInsertContactDBCall(contact: Contact) {
        withContext(Dispatchers.IO) {
            _primaryKeyReturned.value = databaseObject.contactsDao.insertContact(contact = contact)
        }
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