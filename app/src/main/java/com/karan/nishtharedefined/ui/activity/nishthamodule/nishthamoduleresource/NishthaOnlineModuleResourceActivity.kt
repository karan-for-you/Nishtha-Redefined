package com.karan.nishtharedefined.ui.activity.nishthamodule.nishthamoduleresource

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.databinding.ActivityNishthaOnlineModuleResourceBinding

class NishthaOnlineModuleResourceActivity : AppCompatActivity() {

    private lateinit var bindingNishthaOnlineModuleResourceActivity :
            ActivityNishthaOnlineModuleResourceBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingNishthaOnlineModuleResourceActivity = DataBindingUtil.setContentView(
            this,
            R.layout.activity_nishtha_online_module_resource
        )
    }
}