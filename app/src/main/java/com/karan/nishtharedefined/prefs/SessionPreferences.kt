package com.karan.nishtharedefined.prefs

import android.content.Context
import android.content.SharedPreferences

object SessionPreferences {
    private const val SESSION_NAME = "nishthaRedefined"
    private const val CONTEXT_MODE = Context.MODE_PRIVATE
    private lateinit var sharedPreferences: SharedPreferences
    private val DEFAULT_LANGUAGE = Pair("lang","en")

    fun init(context: Context){
        sharedPreferences = context.getSharedPreferences(SESSION_NAME, CONTEXT_MODE)
    }

    var language : String
        get() = sharedPreferences.getString(DEFAULT_LANGUAGE.first, DEFAULT_LANGUAGE.second) ?: ""
        set(value) = sharedPreferences.edit().putString(DEFAULT_LANGUAGE.first,value).apply()
}