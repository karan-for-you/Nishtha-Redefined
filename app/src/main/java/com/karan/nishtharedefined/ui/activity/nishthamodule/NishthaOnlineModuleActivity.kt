package com.karan.nishtharedefined.ui.activity.nishthamodule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.databinding.ActivityNishthaOnlineModulesBinding
import com.karan.nishtharedefined.model.nishthaonline.NishthaLanguageModel
import com.karan.nishtharedefined.ui.adapter.NishthaOnlineModuleAdapter

class NishthaOnlineModuleActivity : AppCompatActivity(), NishthaOnlineModuleAdapter.OnModuleResourceClickListener {

    private lateinit var bindingNishthaOnlineModuleActivity : ActivityNishthaOnlineModulesBinding
    private var language : NishthaLanguageModel? = null
    private var listOfModules = ArrayList<com.karan.nishtharedefined.model.nishthaonline.NishthaModuleModel>()
    private lateinit var nishthaOnlineModuleAdapter : NishthaOnlineModuleAdapter
    private val nishthaOnlineViewModel by lazy {
        val app = requireNotNull(application)
        ViewModelProvider(this, NishthaOnlineLanguageViewModel.Factory(app))
            .get(NishthaOnlineLanguageViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingNishthaOnlineModuleActivity = DataBindingUtil.setContentView(
            this,
            R.layout.activity_nishtha_online_modules
        )
        bindingNishthaOnlineModuleActivity.ivBack.setOnClickListener { onBackPressed() }
        setupAdapter()
        initObserver()
        receiveLanguageAndMakeCall()

    }

    private fun receiveLanguageAndMakeCall(){
        language = intent.getParcelableExtra("language")
        nishthaOnlineViewModel.getNishthaOnlineModuleByLanguage(lang = language?.langCode)
    }

    private fun setupAdapter(){
        nishthaOnlineModuleAdapter = NishthaOnlineModuleAdapter(
            context = this,
            listOfModules = listOfModules,
            onModuleClickListener = this
        )
        bindingNishthaOnlineModuleActivity.rvLanguages.adapter =
            nishthaOnlineModuleAdapter
    }

    private fun initObserver(){
         nishthaOnlineViewModel.nishthaResourceList.observe(
             this,
             { t ->
                 if(t?.isNotEmpty()!!){
                     listOfModules = t
                     nishthaOnlineModuleAdapter.addAllItems(
                         listOfModules = listOfModules
                     )
                 }
             }
         )
    }

    override fun onModuleClicked(nishthaOnlineModule: com.karan.nishtharedefined.model.nishthaonline.NishthaModuleModel) {

    }
}