package com.karan.nishtharedefined.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.LinearLayout
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.const.AppConstants

class LanguageChooseDialog(
    context: Context,
    private val onLanguageSelectedListener: OnLanguageSelectedListener
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.language_dialog)
        initViews()

    }

    private fun initViews() {
        findViewById<LinearLayout>(R.id.llEnglish).setOnClickListener {
            dismiss()
            onLanguageSelectedListener.onLanguageSelected(AppConstants.ENG_FLAG)
        }
        findViewById<LinearLayout>(R.id.llHindi).setOnClickListener {
            dismiss()
            onLanguageSelectedListener.onLanguageSelected(AppConstants.HI_FLAG)
        }
        /*findViewById<LinearLayout>(R.id.llUrdu).setOnClickListener {
            dismiss()
            onLanguageSelectedListener.onLanguageSelected(AppConstants.UR_FLAG)
        }*/
    }

    interface OnLanguageSelectedListener {
        fun onLanguageSelected(lang: String)
    }

}