package com.karan.nishtharedefined

import android.app.Application
import com.karan.nishtharedefined.prefs.SessionPreferences

class NishthaRedefinedApp : Application() {
    override fun onCreate() {
        super.onCreate()
        SessionPreferences.init(this)
    }

    /*override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LanguageManager.setLocale(this)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LanguageManager.setLocale(base))
        MultiDex.install(base)
    }*/


}