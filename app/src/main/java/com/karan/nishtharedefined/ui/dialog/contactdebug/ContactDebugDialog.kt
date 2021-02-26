package com.karan.nishtharedefined.ui.dialog.contactdebug

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.LinearLayout
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.ui.activity.contactsdebugging.AddContactActivity
import com.karan.nishtharedefined.ui.activity.contactsdebugging.ViewContactsDebugging

class ContactDebugDialog(
    var mContext: Context
) : Dialog(mContext) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.contact_debug_dialog)
        initViews()
        setCancelable(true)
    }

    private fun initViews() {
        findViewById<LinearLayout>(R.id.llAddContact).setOnClickListener {
            mContext.startActivity(Intent(mContext,AddContactActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.llViewContact).setOnClickListener {
            mContext.startActivity(Intent(mContext,ViewContactsDebugging::class.java))
        }

    }


}