package com.karan.nishtharedefined.ui.fragment.nishthamodule

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.databinding.ActivityNishthaOnlineLanguageBinding
import com.karan.nishtharedefined.model.NishthaLanguageModel
import com.karan.nishtharedefined.model.NishthaModuleModel
import com.karan.nishtharedefined.ui.adapter.ModuleAdapter
import com.karan.nishtharedefined.utils.Logger

class NishthaOnlineLanguageActivity : AppCompatActivity(), ModuleAdapter.OnModuleClickedListener {

    private lateinit var bindingNishthaOnlineLanguageActivity: ActivityNishthaOnlineLanguageBinding
    private val nishthaOnlineViewModel by lazy {
        val app = requireNotNull(application)
        ViewModelProvider(this, NishthaOnlineLanguageViewModel.Factory(app))
            .get(NishthaOnlineLanguageViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingNishthaOnlineLanguageActivity = DataBindingUtil.setContentView(
            this, R.layout.activity_nishtha_online_language
        )
        initLanguageObserver()
        initModuleObserver()
        nishthaOnlineViewModel.getNishthaOnlineLanguages()
    }

    private fun initLanguageObserver() {
        nishthaOnlineViewModel.nishthaLanguageList.observe(
            this,
            Observer<ArrayList<NishthaLanguageModel>> { t ->
                bindingNishthaOnlineLanguageActivity.llNishthaOnline.removeAllViews()
                if (!t.isNullOrEmpty()) {
                    var count = 0
                    while (count < t.size) {
                        nishthaOnlineViewModel.getNishthaOnlineModuleByLanguage(
                            lang = t[count].langCode
                        )
                        count++
                    }
                }
            }
        )
    }

    private fun initModuleObserver() {
        nishthaOnlineViewModel.nishthaResourceList.observe(
            this,
            Observer<ArrayList<NishthaModuleModel>> { t ->
                debugMyLists(t)

                val headerText = TextView(this)
                headerText.text = when (t[0].modLang) {
                    "en" -> "English"
                    "hi" -> "Hindi"
                    "ur" -> "Urdu"
                    else -> t[0].modLang
                }
                val textParam = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f
                )
                headerText.textSize = 18f
                headerText.layoutParams = textParam
                headerText.gravity = Gravity.CENTER
                headerText.setTextColor(getColor(R.color.tvColor))
                headerText.setBackgroundColor(getColor(R.color.bgHeaderTone))

                val myRecyclerView = RecyclerView(this)
                myRecyclerView.layoutManager = LinearLayoutManager(
                    this, RecyclerView.HORIZONTAL, false
                )
                myRecyclerView.adapter = ModuleAdapter(
                    context = this,
                    listOfModules = t,
                    onModuleClickedListener = this
                )

                bindingNishthaOnlineLanguageActivity.llNishthaOnline.addView(headerText)
                bindingNishthaOnlineLanguageActivity.llNishthaOnline.addView(myRecyclerView)
            }
        )
    }

    private fun debugMyLists(modules: ArrayList<NishthaModuleModel>) {
        for (m in modules)
            Logger.logDebug(m.modLang!!, m.modName!!)
    }

    override fun onModuleClicked(module: NishthaModuleModel) {
        startActivity(Intent(this
            , NishthaOnlineModuleActivity::class.java)
            .putExtra("resource",module))
    }

}