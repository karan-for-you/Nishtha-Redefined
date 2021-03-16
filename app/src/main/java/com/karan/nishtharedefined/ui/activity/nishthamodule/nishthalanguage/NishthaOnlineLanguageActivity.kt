package com.karan.nishtharedefined.ui.activity.nishthamodule.nishthalanguage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.const.AppConstants
import com.karan.nishtharedefined.databinding.ActivityNishthaOnlineLanguageBinding
import com.karan.nishtharedefined.model.nishthaonline.NishthaLanguageModel
import com.karan.nishtharedefined.ui.activity.nishthamodule.nishthamodule.NishthaOnlineModuleActivity
import com.karan.nishtharedefined.ui.adapter.NishthaOnlineLanguageAdapter

class NishthaOnlineLanguageActivity : AppCompatActivity(),
    NishthaOnlineLanguageAdapter.OnLanguageSelectedListener {

    private lateinit var bindingNishthaOnlineLanguageCon: ActivityNishthaOnlineLanguageBinding
    private lateinit var languageAdapter: NishthaOnlineLanguageAdapter
    private val nishthaOnlineViewModel by lazy {
        val app = requireNotNull(application)
        ViewModelProvider(this, NishthaOnlineLanguageViewModel.Factory(app))
            .get(NishthaOnlineLanguageViewModel::class.java)
    }
    private var languageList = ArrayList<NishthaLanguageModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingNishthaOnlineLanguageCon = DataBindingUtil.setContentView(
            this,
            R.layout.activity_nishtha_online_language
        )
        setupAdapter()
        initLanguageObserver()
        initRoomLanguageObserver()
        getLanguages()
    }

    private fun getLanguages() {
        bindingNishthaOnlineLanguageCon.pbLanguage.visibility = View.VISIBLE
        if (isNetworkAvailable())
            nishthaOnlineViewModel.getNishthaOnlineLanguages()
        else
            nishthaOnlineViewModel.makeSelectAllLanguagesCall()

    }

    private fun isNetworkAvailable(): Boolean {
        return true
    }

    private fun setupAdapter() {
        languageAdapter = NishthaOnlineLanguageAdapter(
            languageList = languageList,
            context = this,
            onLanguageSelectedListener = this
        )
        bindingNishthaOnlineLanguageCon.rvLanguages.apply {
            layoutManager = GridLayoutManager(this.context,2)
            adapter = languageAdapter
        }
    }

    private fun initLanguageObserver() {
        nishthaOnlineViewModel.nishthaLanguageList.observe(
            this,
            { t ->
                bindingNishthaOnlineLanguageCon.pbLanguage.visibility = View.GONE
                if (t!!.isNotEmpty()) {
                    // Notify the Adapter to update the items
                    // languageAdapter.addAllItems(t)
                    // Insert all the received languages in the Language Table
                    // TODO: Check of the callback of bulk insertion returns something
                    nishthaOnlineViewModel.makeInsertLanguageDBCall(t)
                    nishthaOnlineViewModel.makeSelectAllLanguagesCall()
                }
                //TODO: Make Database call regardless to retrieve
            }
        )
    }

    private fun initRoomLanguageObserver() {
        nishthaOnlineViewModel.nishthaLanguageListRoom.observe(
            this,
            { t ->
                if (t!!.isNotEmpty()) {
                    val mediatedList = ArrayList<NishthaLanguageModel>()
                    for (model in t) {
                        mediatedList.add(
                            NishthaLanguageModel(
                                model.langCode,
                                model.langText,
                                model.langName
                            )
                        )
                    }
                    languageAdapter.addAllItems(mediatedList)
                }
            }
        )
    }

    override fun onLanguageSelected(languageModel: NishthaLanguageModel) {
        startActivity(
            Intent(this, NishthaOnlineModuleActivity::class.java)
                .putExtra(AppConstants.NISHTHA_ONLINE_LANGUAGE, languageModel)
        )
    }

}