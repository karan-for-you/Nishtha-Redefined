package com.karan.nishtharedefined

import android.text.TextWatcher
import android.widget.EditText
import androidx.databinding.BindingAdapter

class BindingAdapters {
    companion object{
        @JvmStatic @BindingAdapter("onEmailChanged")
        fun onEmailChangedListener(view : EditText, listener: TextWatcher){
            view.addTextChangedListener(listener)
        }

        @JvmStatic @BindingAdapter("onPasswordChanged")
        fun onPasswordChangedListener(view : EditText, listener: TextWatcher){
            view.addTextChangedListener(listener)
        }
    }
}