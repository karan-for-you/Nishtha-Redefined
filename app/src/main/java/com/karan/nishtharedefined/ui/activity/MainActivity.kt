package com.karan.nishtharedefined.ui.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.databinding.ActivityMainBinding
import com.karan.nishtharedefined.utils.LanguageManager

class MainActivity : AppCompatActivity() {

    private lateinit var bindingMainActivity : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMainActivity = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val navController = this.findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return NavigationUI.navigateUp(navController, bindingMainActivity.drawerLayout)
    }


    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LanguageManager.setLocale(newBase))
    }
}