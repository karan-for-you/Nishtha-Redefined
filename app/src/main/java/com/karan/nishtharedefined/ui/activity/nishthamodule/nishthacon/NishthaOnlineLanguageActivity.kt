package com.karan.nishtharedefined.ui.activity.nishthamodule.nishthacon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.databinding.ActivityNishthaOnlineLanguageConBinding
import com.karan.nishtharedefined.model.nishthaonline.NishthaLanguageModel
import com.karan.nishtharedefined.ui.activity.nishthamodule.nishtharedefined.NishthaOnlineLanguageViewModel
import com.karan.nishtharedefined.ui.adapter.NishthaOnlineLanguageConAdapter

class NishthaOnlineLanguageActivity : AppCompatActivity() {

    private lateinit var bindingNishthaOnlineLanguageCon : ActivityNishthaOnlineLanguageConBinding
    private lateinit var languageAdapter : NishthaOnlineLanguageConAdapter
    private val nishthaOnlineViewModel by lazy {
        val app = requireNotNull(application)
        ViewModelProvider(this, NishthaOnlineLanguageViewModel.Factory(app))
            .get(NishthaOnlineLanguageViewModel::class.java)
    }
    var languageList = ArrayList<NishthaLanguageModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingNishthaOnlineLanguageCon = DataBindingUtil.setContentView(
            this,
            R.layout.activity_nishtha_online_language_con
        )
        setupAdapter()
        initLanguageObserver()
        getLanguages()
    }

    private fun getLanguages(){
        bindingNishthaOnlineLanguageCon.pbLanguage.visibility = View.VISIBLE
        nishthaOnlineViewModel.getNishthaOnlineLanguages()
    }

    private fun setupAdapter(){
        languageAdapter = NishthaOnlineLanguageConAdapter(
            languageList = languageList,
            context = this)
        bindingNishthaOnlineLanguageCon.rvLanguages.adapter =
            languageAdapter
    }

    private fun initLanguageObserver(){
        nishthaOnlineViewModel.nishthaLanguageList.observe(
            this,
            { t ->
                if(t!!.isNotEmpty())
                    languageAdapter.addAllItems(t)

                //TODO: Make Database call regardless

            }
        )
    }

}