package com.karan.nishtharedefined.ui.activity.nishthamodule.nishthamoduleresource

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.databinding.ActivityNishthaOnlineModuleResourceBinding
import com.karan.nishtharedefined.model.nishthaonline.NishthaModuleModel
import com.karan.nishtharedefined.model.nishthaonline.NishthaOnlineModuleResourceModel
import com.karan.nishtharedefined.ui.adapter.NishthaOnlineModuleAdapter
import com.karan.nishtharedefined.ui.adapter.NishthaOnlineModuleResourceAdapter
import com.karan.nishtharedefined.utils.InternetUtils

class NishthaOnlineModuleResourceActivity : AppCompatActivity(), NishthaOnlineModuleResourceAdapter.OnModuleResourceClickListener {

    private lateinit var bindingNishthaOnlineModuleResourceActivity:
            ActivityNishthaOnlineModuleResourceBinding
    private val nishthaOnlineModuleResourceViewModel by lazy {
        ViewModelProvider(this, NishthaOnlineModuleResourceViewModel.Factory(application))
            .get(NishthaOnlineModuleResourceViewModel::class.java)
    }
    private var module: NishthaModuleModel? = null
    private var nishthaOnlineModuleResourceAdapter : NishthaOnlineModuleResourceAdapter? = null

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
        module = intent.extras?.get("module") as NishthaModuleModel
        if(InternetUtils.getConnectionType(this)!=0)
            nishthaOnlineModuleResourceViewModel.getModuleResources(
                lang = module?.modLang,
                modId = module?.modId
            )
    }

    private fun setupAdapter(listOfModules: ArrayList<NishthaOnlineModuleResourceModel>) {
        nishthaOnlineModuleResourceAdapter = NishthaOnlineModuleResourceAdapter(
            context = this,
            listOfModuleResources = listOfModules,
            onModuleClickListener = this
        )
        bindingNishthaOnlineModuleResourceActivity.rvLanguages.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = nishthaOnlineModuleResourceAdapter
        }
    }

    private fun initResourcesObserver() {
        nishthaOnlineModuleResourceViewModel.moduleResourceList.observe(
            this,
            { t ->
                if (t?.isNotEmpty()!!) {
                    nishthaOnlineModuleResourceViewModel.makeInsertResourcesCall(
                        t
                    )
                }
            }
        )
    }

    override fun onModuleResourceClicked() {

    }


}