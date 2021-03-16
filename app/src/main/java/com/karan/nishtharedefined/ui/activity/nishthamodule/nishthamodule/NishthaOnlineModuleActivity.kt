package com.karan.nishtharedefined.ui.activity.nishthamodule.nishthamodule

import android.os.Bundle
import android.view.View
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
import com.karan.nishtharedefined.utils.InternetUtils
import com.karan.nishtharedefined.utils.Logger

class NishthaOnlineModuleActivity : AppCompatActivity(),
    NishthaOnlineModuleAdapter.OnModuleResourceClickListener {

    private lateinit var bindingNishthaOnlineModuleActivity: ActivityNishthaOnlineModulesBinding
    private var language: NishthaLanguageModel? = null
    //private var listOfModules = ArrayList<NishthaModuleModel>()
    private var modLang : String? = ""
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
        initModulesObserver()
        initModulesObserverRoom()
        receiveLanguageAndMakeCall()
    }

    private fun receiveLanguageAndMakeCall() {
        language = intent.getParcelableExtra(AppConstants.NISHTHA_ONLINE_LANGUAGE)
        bindingNishthaOnlineModuleActivity.tvLanguage.text =
            language?.langName
        modLang = language?.langText
        Logger.logDebug(
            "Language Object ", language?.langCode +
                    " " + language?.langName + " " + language?.langText
        )
        bindingNishthaOnlineModuleActivity.pbLanguage.visibility = View.VISIBLE
        if(InternetUtils.checkConnection(this))
            nishthaOnlineViewModel.getNishthaOnlineModuleByLanguage(lang = modLang)
        else
            nishthaOnlineViewModel.makeSelectAllModulesDBCall(langCode = modLang)
    }

    private fun setupAdapter(listOfModules : ArrayList<NishthaModuleModel>) {
        nishthaOnlineModuleAdapter = NishthaOnlineModuleAdapter(
            context = this,
            listOfModules = listOfModules,
            onModuleClickListener = this
        )
        bindingNishthaOnlineModuleActivity.rvLanguages.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = nishthaOnlineModuleAdapter
        }
    }

    private fun initModulesObserver() {
        nishthaOnlineViewModel.nishthaResourceList.observe(
            this,
            { t ->
                bindingNishthaOnlineModuleActivity.pbLanguage.visibility = View.GONE
                bindingNishthaOnlineModuleActivity.flConnectionStatus.visibility = View.VISIBLE
                if (t?.isNotEmpty()!!) {
                    // TODO: Add a new LiveData Object to learn if Room Insertion has been a success or not
                    // TODO: To avoid getting the list size of 0, we are reading, the Modules from Room
                    // TODO: Insertion is not completing in time
                    // TODO: The Long Value must return something which will be read
                    nishthaOnlineViewModel.makeInsertModulesDBCall(t)
                    nishthaOnlineViewModel.makeSelectAllModulesDBCall(modLang)
                }
            }
        )
    }

    private fun initModulesObserverRoom() {
        nishthaOnlineViewModel.nishthaModulesListRoom.observe(
            this,
            { t ->
                bindingNishthaOnlineModuleActivity.pbLanguage.visibility = View.GONE
                bindingNishthaOnlineModuleActivity.flConnectionStatus.visibility = View.VISIBLE
                if (InternetUtils.checkConnection(this)) {
                    bindingNishthaOnlineModuleActivity.ivCheck.visibility = View.VISIBLE
                    bindingNishthaOnlineModuleActivity.ivCross.visibility = View.GONE
                } else{
                    bindingNishthaOnlineModuleActivity.ivCheck.visibility = View.GONE
                    bindingNishthaOnlineModuleActivity.ivCross.visibility = View.VISIBLE
                    bindingNishthaOnlineModuleActivity.flDatabaseStatus.visibility = View.VISIBLE
                }
                bindingNishthaOnlineModuleActivity.flConnectionStatus.visibility = View.VISIBLE
                if (t!!.isNotEmpty()) {
                    val mediatedList = ArrayList<NishthaModuleModel>()
                    for (model in t)
                        mediatedList.add(
                            NishthaModuleModel(
                                modName = model.modName,
                                modId = model.modId,
                                modLang = model.modLang
                            )
                        )
                    //nishthaOnlineModuleAdapter.addAllItems(mediatedList)
                    setupAdapter(mediatedList)
                }
            })
    }

    override fun onModuleClicked(nishthaOnlineModule: NishthaModuleModel) {

    }
}