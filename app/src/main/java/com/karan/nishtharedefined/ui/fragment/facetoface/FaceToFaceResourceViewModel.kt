package com.karan.nishtharedefined.ui.fragment.facetoface

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalStateException


class FaceToFaceResourceViewModel(app : Application) : AndroidViewModel(app) {

    @Suppress("UNCHECKED_CAST")
    class Factory(val app : Application) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(FaceToFaceResourceViewModel::class.java))
                return FaceToFaceResourceViewModel(app) as T
            throw IllegalAccessException("Can't create Face To Face Resource View Model")
        }

    }
}