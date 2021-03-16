package com.karan.nishtharedefined.ui.activity.nishthamodule.nishthamodule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.const.AppConstants
import com.karan.nishtharedefined.databinding.ActivityNishthaOnlineModulesBinding
import com.karan.nishtharedefined.model.nishthaonline.NishthaLanguageModel
import com.karan.nishtharedefined.model.nishthaonline.NishthaModuleModel
import com.karan.nishtharedefined.ui.activity.nishthamodule.nishthalanguage.NishthaOnlineLanguageViewModel
import com.karan.nishtharedefined.ui.adapter.NishthaOnlineModuleAdapter
import com.karan.nishtharedefined.utils.Logger

class NishthaOnlineModuleActivity : AppCompatActivity(),
    NishthaOnlineModuleAdapter.OnModuleResourceClickListener {

    private lateinit var bindingNishthaOnlineModuleActivity: ActivityNishthaOnlineModulesBinding
    private var language: NishthaLanguageModel? = null
    private lateinit var nishthaOnlineModuleAdapter: NishthaOnlineModuleAdapter
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
        //setupAdapter()
        initObserver()
        receiveLanguageAndMakeCall()

    }

    private fun receiveLanguageAndMakeCall() {
        language = intent.getParcelableExtra(AppConstants.NISHTHA_ONLINE_LANGUAGE)
        bindingNishthaOnlineModuleActivity.tvLanguage.text =
            language?.langName
        Logger.logDebug(
            "Language", language?.langCode +
                    " " + language?.langName + " " + language?.langText
        )
        nishthaOnlineViewModel.getNishthaOnlineModuleByLanguage(lang = language?.langText)
    }

    private fun setupAdapter(listOfModules : ArrayList<NishthaModuleModel> ) {
        nishthaOnlineModuleAdapter = NishthaOnlineModuleAdapter(
            context = this,
            listOfModules = listOfModules,
            onModuleClickListener = this
        )
        bindingNishthaOnlineModuleActivity.rvLanguages.layoutManager =
            LinearLayoutManager(this)
        bindingNishthaOnlineModuleActivity.rvLanguages.adapter =
            nishthaOnlineModuleAdapter
        nishthaOnlineViewModel.makeInsertModulesDBCall(listOfModules)
    }

    private fun initObserver() {
        nishthaOnlineViewModel.nishthaResourceList.observe(
            this,
            { t ->
                if (t?.isNotEmpty()!!)
                    setupAdapter(t)
            }
        )
    }

    override fun onModuleClicked(nishthaOnlineModule: com.karan.nishtharedefined.model.nishthaonline.NishthaModuleModel) {

    }
}