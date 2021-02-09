package com.karan.nishtharedefined.utils

import android.content.Context
import com.karan.nishtharedefined.BuildConfig
import java.io.File
import java.util.*

class AppUtils {

    companion object {
        private val TAG = AppUtils::class.java.simpleName
        fun createDirectory(context: Context) {
            val myDir = File(getFilteredPath(context))
            if (!myDir.exists()) {
                Logger.logDebug(TAG, "Directory doesn't exist")
                if (myDir.mkdir())
                    Logger.logDebug(TAG, "Directory Created")
                else
                    Logger.logDebug(TAG, "Error creating directory")
            } else
                Logger.logDebug(TAG, "Directory already exists")
        }

        fun getFilteredPath(context: Context): String {
            val actualPath =
                context.getExternalFilesDir(null)?.absolutePath + "/nishthaRedefined"
            Logger.logDebug("Actual Path", actualPath)

            val removablePathSub = "Android/data/" + BuildConfig.APPLICATION_ID +
                    File.separator.toString() + "files" + File.separator
            Logger.logDebug("Removal Path Sub string", removablePathSub)

            return actualPath.replace(removablePathSub, "")
        }


        fun fileExt(url: String): String? {
            var urlReceived = url
            if (urlReceived.indexOf("?") > -1) {
                urlReceived = urlReceived.substring(0, urlReceived.indexOf("?"))
            }
            return if (urlReceived.lastIndexOf(".") == -1) {
                null
            } else {
                var ext = urlReceived.substring(urlReceived.lastIndexOf(".") + 1)
                if (ext.indexOf("%") > -1) {
                    ext = ext.substring(0, ext.indexOf("%"))
                }
                if (ext.indexOf("/") > -1) {
                    ext = ext.substring(0, ext.indexOf("/"))
                }
                ext.toLowerCase(Locale.ROOT)
            }
        }
    }
}