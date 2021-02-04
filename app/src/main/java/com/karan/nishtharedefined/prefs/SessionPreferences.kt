package com.karan.nishtharedefined.prefs

import android.content.Context
import android.content.SharedPreferences
import com.karan.nishtharedefined.const.AppConstants

object SessionPreferences {
    private const val SESSION_NAME = "nishthaRedefined"
    private const val CONTEXT_MODE = Context.MODE_PRIVATE
    private lateinit var sharedPreferences: SharedPreferences
    private val DEFAULT_LANGUAGE = Pair("lang",AppConstants.ENG_FLAG)

    fun init(context: Context){
        sharedPreferences = context.getSharedPreferences(SESSION_NAME, CONTEXT_MODE)
    }

    // The ?: operator will pick the right hand value if the left hand value is null
    var language : String
        get() = sharedPreferences.getString(DEFAULT_LANGUAGE.first, DEFAULT_LANGUAGE.second) ?: AppConstants.ENG_FLAG
        set(value) = sharedPreferences.edit().putString(DEFAULT_LANGUAGE.first,value).apply()


}