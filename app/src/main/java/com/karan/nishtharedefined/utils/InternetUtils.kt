package com.karan.nishtharedefined.utils

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService


object InternetUtils {
    fun checkConnection(ctx : Context) : Boolean{
        val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val netInfo = cm!!.activeNetworkInfo
        return netInfo != null && netInfo.isConnected && netInfo.isAvailable
    }
}