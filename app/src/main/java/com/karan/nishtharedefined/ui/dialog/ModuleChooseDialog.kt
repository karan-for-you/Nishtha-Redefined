package com.karan.nishtharedefined.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.LinearLayout
import com.karan.nishtharedefined.R

class ModuleChooseDialog(
    context: Context,
    var onModuleOptionSelectedListener: OnModuleOptionSelectedListener
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.module_choose_dialog)
        initViews()

    }

    private fun initViews(){
        findViewById<LinearLayout>(R.id.llFaceToFace).setOnClickListener {
            dismiss()
            onModuleOptionSelectedListener.onModuleOptionSelected(1)
        }
        findViewById<LinearLayout>(R.id.llNishthaModule).setOnClickListener {
            dismiss()
            onModuleOptionSelectedListener.onModuleOptionSelected(2)
        }
    }

    interface OnModuleOptionSelectedListener {
        fun onModuleOptionSelected(id: Int)
    }

}