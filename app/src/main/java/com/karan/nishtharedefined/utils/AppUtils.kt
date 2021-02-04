package com.karan.nishtharedefined.utils

import android.content.Context
import com.karan.nishtharedefined.BuildConfig
import java.io.File

class AppUtils {

    companion object {
        private val TAG = AppUtils::class.java.simpleName
        fun createDirectory(context: Context) {
            val actualPath =
                context.getExternalFilesDir(null)?.absolutePath+"/nishthaRedefined"

            Logger.logDebug("Actual Path",actualPath)
            val extraPortion = "Android/data/" + BuildConfig.APPLICATION_ID +
                    File.separator.toString() + "files" + File.separator

            Logger.logDebug("Extra Portion",extraPortion)
            val filteredPath: String = actualPath.replace(extraPortion, "")

            Logger.logDebug("New Path",filteredPath)

            val myDir = File(filteredPath)
            if (!myDir.exists()) {
                Logger.logDebug(TAG, "Directory doesn't exist")
                if (myDir.mkdir())
                    Logger.logDebug(TAG, "Directory Created")
                else
                    Logger.logDebug(TAG, "Error creating directory")
            } else
                Logger.logDebug(TAG, "Directory already exists")
        }
    }
}