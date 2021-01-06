package com.karan.nishtharedefined

import android.app.Application
import com.karan.nishtharedefined.prefs.SessionPreferences

class NishthaRedefinedApp : Application() {
    override fun onCreate() {
        super.onCreate()
        SessionPreferences.init(this)
    }
}