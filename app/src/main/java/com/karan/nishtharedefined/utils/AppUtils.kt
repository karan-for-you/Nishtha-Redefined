package com.karan.nishtharedefined.utils

import android.content.Context
import java.io.File

class AppUtils {

    companion object{
        private val TAG = AppUtils::class.java.simpleName
        fun createDirectory(context : Context){
            val nishthaRedefined = File(
                context.getExternalFilesDir(null)?.absolutePath+"/nishthaRedefined/"
            )
            if(!nishthaRedefined.exists()) {
                Logger.logDebug(TAG, "Directory doesn't exist")
                if (nishthaRedefined.mkdir())
                    Logger.logDebug(TAG, "Directory Created")
                else
                    Logger.logDebug(TAG, "Error creating directory")
            } else
                Logger.logDebug(TAG, "Directory already exists")
        }
    }
}