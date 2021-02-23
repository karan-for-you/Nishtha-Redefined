package com.karan.nishtharedefined.ui.dialog.contactdebug

import android.app.Application
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.karan.nishtharedefined.R

class ContactDebugDialog(
    context: Context,
    var application: Application,
    var viewStoreModelOwner: ViewModelStoreOwner
) : Dialog(context) {

    private var tvSave: TextView? = null
    private var tvView: TextView? = null
    private var etName: EditText? = null
    private var etContactNumber: EditText? = null
    private val contactDebugDialogViewModel by lazy {
        ViewModelProvider(
            viewStoreModelOwner,
            ContactDebugDialogViewModel.Factory(
                application
            )
        ).get(ContactDebugDialogViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.contact_debug_dialog)
        assignIds()
        initViews()
        setCancelable(true)
    }

    private fun assignIds() {
        tvSave = findViewById(R.id.tvSave)
        etName = findViewById(R.id.etName)
        etContactNumber = findViewById(R.id.etContactNumber)

    }

    private fun initViews() {
        tvSave?.setOnClickListener {
            validateData()
        }
        tvView?.setOnClickListener {

        }
    }

    private fun validateData() {
        if (etName?.text?.trim().toString().isEmpty()) {
            if (etContactNumber?.text?.trim().toString().isEmpty()) {
                contactDebugDialogViewModel.getContactsSize()
            } else Toast.makeText(context, "Please enter contact number", Toast.LENGTH_SHORT).show()
        } else Toast.makeText(context, "Please enter Name", Toast.LENGTH_SHORT).show()
    }


}