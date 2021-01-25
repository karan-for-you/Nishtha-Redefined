package com.karan.nishtharedefined.utils

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import com.karan.nishtharedefined.prefs.SessionPreferences
import java.util.*

object LanguageManager {

    // Only to return the context of the newly set Locale - More Like getLocale
    fun setLocale(context: Context?): Context {
        return updateResources(context!!, getCurrentLanguage(context))
    }

    fun setNewLocale(context: Context, language: String) {
        SessionPreferences.language = language
        updateResources(context, language) // The method to decide whether to go for Legacy one or new one
    }

    private fun getCurrentLanguage(context: Context?) : String{
        SessionPreferences.init(context!!)
        return SessionPreferences.language
    }

    // This method will be called on a normal run and on Recreation of the activity too
    private fun updateResources(context: Context, language: String): Context {
        return updateResourcesNew(context, language)
    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResourcesNew(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration = context.resources.configuration
        // Setting the Locale
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale) // For Urdu - Auto RTL
        // returning the fresh config context
        return context.createConfigurationContext(configuration)
    }

    private fun updateResourcesLegacy(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources = context.resources
        val configuration = resources.configuration
        // Setting the Locale
        configuration.locale = locale
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLayoutDirection(locale) // For Urdu - Auto RTL
        }
        resources.updateConfiguration(configuration, resources.displayMetrics)
        // Returning the same context that was received via Parameter
        return context
    }
}