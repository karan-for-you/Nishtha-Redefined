package com.karan.nishtharedefined.ui.dialog.contactdebug

import android.app.Application
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.database.NishthaRedefinedDatabaseBuilder
import com.karan.nishtharedefined.database.dataobjects.Contact
import com.karan.nishtharedefined.databinding.ContactDebugDialogBinding
import com.karan.nishtharedefined.ui.activity.LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactDebugDialog(
    context: Context,
    var application: Application,
    var viewStoreModelOwner: ViewModelStoreOwner
) : Dialog(context) {

    private lateinit var bindingContactDebugDialog: ContactDebugDialogBinding
    private val contactDebugDialogViewModel by lazy {
        ViewModelProvider(
            viewStoreModelOwner, ContactDebugDialogViewModel.Factory(
                application
            )
        ).get(ContactDebugDialogViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.contact_debug_dialog)
        setCancelable(true)
        initViews()
    }

    fun initViews() {
        bindingContactDebugDialog.tvSave.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        if (bindingContactDebugDialog.etName.text.trim().toString().isEmpty()) {
            if (bindingContactDebugDialog.etContactNumber.text.trim().toString().isEmpty()) {
                contactDebugDialogViewModel.getContactsSize()
            } else Toast.makeText(context, "Please enter contact number", Toast.LENGTH_SHORT).show()
        } else Toast.makeText(context, "Please enter Name", Toast.LENGTH_SHORT).show()
    }


}