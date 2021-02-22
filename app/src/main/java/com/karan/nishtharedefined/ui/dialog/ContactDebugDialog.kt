package com.karan.nishtharedefined.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.karan.nishtharedefined.R

class ContactDebugDialog(context: Context) : Dialog(context){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.contact_debug_dialog)
    }

    fun initViews(){

    }
}