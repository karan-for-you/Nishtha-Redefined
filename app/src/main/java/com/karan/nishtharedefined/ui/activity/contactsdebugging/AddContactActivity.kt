package com.karan.nishtharedefined.ui.activity.contactsdebugging

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.databinding.ActivityAddContactBinding
import com.karan.nishtharedefined.db.Contact
import com.karan.nishtharedefined.ui.dialog.contactdebug.ContactsDebugViewModel
import com.karan.nishtharedefined.utils.Logger

class AddContactActivity : AppCompatActivity() {

    private lateinit var bindingAddContactActivity: ActivityAddContactBinding
    private val contactDebugViewModel by lazy {
        ViewModelProvider(
            this,
            ContactsDebugViewModel.Factory(
                application
            )
        ).get(ContactsDebugViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingAddContactActivity =
            DataBindingUtil.setContentView(
                this,
                R.layout.activity_add_contact
            )
        initViews()
        initReadNumberOfRecordsObserver()
        initAddContactObserver()
    }

    private fun initReadNumberOfRecordsObserver(){
        contactDebugViewModel.numberOfContacts.observe(
            this,
            { t ->
                contactDebugViewModel.postContact(
                    Contact(
                        id = t.inc(),
                        bindingAddContactActivity.etName.text.toString().trim(),
                        bindingAddContactActivity.etContactNumber.text.toString().trim()
                    )
                )
            }
        )
    }

    private fun initAddContactObserver(){
        contactDebugViewModel.primaryKeyReturned.observe(
                this,
                { t ->
                    bindingAddContactActivity.pbDatabaseCall.visibility = View.GONE
                    Logger.logDebug("Primary Key",t.toString())
                }
        )
    }

    private fun initViews() {
        bindingAddContactActivity.tvSave.setOnClickListener {
            bindingAddContactActivity.pbDatabaseCall.visibility = View.VISIBLE
            validateData()
        }
        bindingAddContactActivity.tvView.setOnClickListener {
            showData()
        }
        bindingAddContactActivity.tvDeleteContacts.setOnClickListener {
            deleteContacts()
        }
    }

    private fun validateData() {
        if (bindingAddContactActivity.etName.text?.trim().toString().isNotEmpty()) {
            if (bindingAddContactActivity.etContactNumber.text?.trim().toString().isNotEmpty()) {
                contactDebugViewModel.getContactsSize()
            } else Toast.makeText(this, "Please enter contact number", Toast.LENGTH_SHORT).show()
        } else Toast.makeText(this, "Please enter Name", Toast.LENGTH_SHORT).show()
    }

    private fun showData() {
        contactDebugViewModel.getContactSizeOnly()
    }

    private fun deleteContacts() {
        contactDebugViewModel.deleteContacts()
    }

}