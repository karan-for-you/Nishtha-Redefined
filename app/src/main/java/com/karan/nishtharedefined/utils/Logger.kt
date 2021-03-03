package com.karan.nishtharedefined.utils

import android.util.Log
import com.karan.nishtharedefined.BuildConfig

class Logger {

    companion object {
        private val DEBUG_MODE = BuildConfig.DEBUG

        fun logDebug(tag: String, message: String) {
            if (validateArguments(tag, message))
                Log.d(tag, message)
        }

        fun logError(tag: String, message: String) {
            if (validateArguments(tag, message))
                Log.e(tag, message)
        }

        fun logVerbose(tag: String, message: String) {
            if (DEBUG_MODE && validateArguments(tag, message))
                Log.v(tag, message)
        }

        fun logInfo(tag: String, message: String) {
            if (DEBUG_MODE && validateArguments(tag, message))
                Log.i(tag, message)
        }

        fun logWarning(tag: String, message: String) {
            if (DEBUG_MODE && validateArguments(tag, message))
                Log.w(tag, message)
        }

        /**
         *  Use the following methods if they are absolutely necessary
         *  for the purpose of keeping an eye on values that are to be
         *  observed after signing an APK. Tokens and other data should
         *  not be compromised at any cost.
         *
         *  WARNING : UNDER NO CIRCUMSTANCES A BUILD SHOULD EVER BE DEPLOYED
         *  ON GOOGLE PLAY STORE WITH THESE METHODS BEING USED IN THE
         *  APPLICATION
         */

        fun logDebugRelease(tag: String, message: String) {
            if (validateArguments(tag, message))
                Log.d(tag, message)
        }

        fun logErrorRelease(tag: String, message: String) {
            if (validateArguments(tag, message))
                Log.e(tag, message)
        }

        fun logVerboseRelease(tag: String, message: String) {
            if (validateArguments(tag, message))
                Log.v(tag, message)
        }

        fun logInfoRelease(tag: String, message: String) {
            if (validateArguments(tag, message))
                Log.i(tag, message)
        }

        fun logWarningRelease(tag: String, message: String) {
            if (validateArguments(tag, message))
                Log.w(tag, message)
        }

        private fun validateArguments(tag: String, message: String): Boolean {
            return (tag.isNotEmpty() && message.isNotEmpty())
        }
    }

}