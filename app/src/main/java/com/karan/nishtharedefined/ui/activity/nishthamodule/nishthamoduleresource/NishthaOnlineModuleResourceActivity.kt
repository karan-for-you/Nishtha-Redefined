package com.karan.nishtharedefined.ui.activity.nishthamodule.nishthamoduleresource

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.databinding.ActivityNishthaOnlineModuleResourceBinding
import com.karan.nishtharedefined.model.nishthaonline.NishthaModuleModel
import com.karan.nishtharedefined.model.nishthaonline.NishthaOnlineModuleResourceModel
import com.karan.nishtharedefined.ui.adapter.NishthaOnlineModuleResourceAdapter
import com.karan.nishtharedefined.utils.InternetUtils

class NishthaOnlineModuleResourceActivity : AppCompatActivity(),
    NishthaOnlineModuleResourceAdapter.OnModuleResourceClickListener {

    private lateinit var bindingNishthaOnlineModuleResourceActivity:
            ActivityNishthaOnlineModuleResourceBinding
    private val nishthaOnlineModuleResourceViewModel by lazy {
        ViewModelProvider(this, NishthaOnlineModuleResourceViewModel.Factory(application))
            .get(NishthaOnlineModuleResourceViewModel::class.java)
    }
    private var module: NishthaModuleModel? = null
    private var nishthaOnlineModuleResourceAdapter: NishthaOnlineModuleResourceAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingNishthaOnlineModuleResourceActivity = DataBindingUtil.setContentView(
            this,
            R.layout.activity_nishtha_online_module_resource
        )
        initResourcesCallObserver()
        initIDSObserver()
        initResourcesRoomObserver()
        receiveModuleInfo()
    }

    private fun receiveModuleInfo() {
        module = intent.extras?.get("module") as NishthaModuleModel
        // TODO: Wrap this with Database call to check if the Module has been
        //  accessed before and Insertion in Table has actually occurred or not
        //  PREFERABLY RETURN A COUNT..
        if (InternetUtils.getConnectionType(this) != 0)
            // Network Call
            nishthaOnlineModuleResourceViewModel.getModuleResources(
                lang = module?.modLang,
                modId = module?.modId
            )
        else
            // Database Call
            nishthaOnlineModuleResourceViewModel.makeSelectResourcesCall(
                lang = module?.modLang!!,
                moduleId = module?.modId!!
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

    private fun initResourcesCallObserver() {
        nishthaOnlineModuleResourceViewModel.moduleResourceList.observe(
            this,
            { t ->
                if (t?.isNotEmpty()!!)
                    nishthaOnlineModuleResourceViewModel.makeInsertResourcesCall(
                        t, module?.modId!!, module?.modLang!!
                    )
            }
        )
    }

    private fun initIDSObserver() {
        nishthaOnlineModuleResourceViewModel.insertedIds.observe(
            this,
            {
                nishthaOnlineModuleResourceViewModel.makeSelectResourcesCall(
                    module?.modLang!!,
                    module?.modId!!
                )
            }
        )
    }

    private fun initResourcesRoomObserver() {
        nishthaOnlineModuleResourceViewModel.moduleResourceListRoom.observe(
            this,
            { t ->
                if (t?.isNotEmpty()!!) {
                    val mediatedList = ArrayList<NishthaOnlineModuleResourceModel>()
                    for (model in t)
                        mediatedList.add(
                            NishthaOnlineModuleResourceModel(
                                resource__name = model.resource__name,
                                resource__link = model.resource__link,
                                resource__type = model.resource__type,
                                resource__html = model.resource__html
                            )
                        )
                    setupAdapter(mediatedList)
                }
            }
        )
    }

    override fun onModuleResourceClicked() {
        //TODO: Handle Action here on the basis of type of the resource
    }


}