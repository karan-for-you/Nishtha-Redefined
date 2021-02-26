package com.karan.nishtharedefined.ui.activity.contactsdebugging

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.databinding.ActivityAddContactBinding
import com.karan.nishtharedefined.ui.dialog.contactdebug.ContactsDebugViewModel

class AddContactActivity : AppCompatActivity() {

    private lateinit var bindingAddContactActivity : ActivityAddContactBinding
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
            DataBindingUtil.setContentView(this,
                R.layout.activity_add_contact)
        initViews()
    }

    private fun initViews() {
        bindingAddContactActivity.tvSave.setOnClickListener {
            validateData()
        }
        bindingAddContactActivity.tvView.setOnClickListener {

        }
    }

    private fun validateData() {
        if (bindingAddContactActivity.etName.text?.trim().toString().isEmpty()) {
            if (bindingAddContactActivity.etContactNumber.text?.trim().toString().isEmpty()) {
                contactDebugViewModel.getContactsSize()
            } else Toast.makeText(this, "Please enter contact number", Toast.LENGTH_SHORT).show()
        } else Toast.makeText(this, "Please enter Name", Toast.LENGTH_SHORT).show()
    }
}