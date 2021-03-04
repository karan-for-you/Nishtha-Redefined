package com.karan.nishtharedefined.ui.activity.nishthamodule.nishthamoduleresource

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.databinding.ActivityNishthaOnlineModuleResourceBinding
import com.karan.nishtharedefined.model.nishthaonline.NishthaModuleModel
import com.karan.nishtharedefined.model.nishthaonline.NishthaOnlineModuleResourceModel

class NishthaOnlineModuleResourceActivity : AppCompatActivity() {

    private lateinit var bindingNishthaOnlineModuleResourceActivity:
            ActivityNishthaOnlineModuleResourceBinding
    private val nishthaOnlineModuleResourceViewModel by lazy {
        ViewModelProvider(this, NishthaOnlineModuleResourceViewModel.Factory(application))
            .get(NishthaOnlineModuleResourceViewModel::class.java)
    }
    private var moduleInfo: NishthaModuleModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingNishthaOnlineModuleResourceActivity = DataBindingUtil.setContentView(
            this,
            R.layout.activity_nishtha_online_module_resource
        )
        initResourcesObserver()
        receiveModuleInfo()
    }

    private fun receiveModuleInfo() {
        moduleInfo = intent.extras?.get("moduleInfo") as NishthaModuleModel
        nishthaOnlineModuleResourceViewModel.getModuleResources(
            lang = moduleInfo?.modLang!!,
            catId = moduleInfo?.modId!!
        )
    }

    private fun initResourcesObserver() {
        nishthaOnlineModuleResourceViewModel.moduleResourceList.observe(
            this,
            { t ->
                if (t?.isNotEmpty()!!) {

                }
            }
        )
    }


}