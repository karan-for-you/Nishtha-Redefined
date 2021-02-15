package com.karan.nishtharedefined.ui.fragment.nishthamodule

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.databinding.NishthaOnlineLanguageFragmentBinding
import com.karan.nishtharedefined.model.NishthaLanguageModel
import com.karan.nishtharedefined.model.NishthaModuleModel
import com.karan.nishtharedefined.ui.activity.MainActivity
import com.karan.nishtharedefined.ui.adapter.ModuleAdapter
import com.karan.nishtharedefined.utils.Logger

class NishthaOnlineLanguageFragment : Fragment(), ModuleAdapter.OnModuleClickedListener {

    private lateinit var bindingNishthaModule: NishthaOnlineLanguageFragmentBinding
    private val nishthaOnlineViewModel by lazy {
        val app = requireNotNull(activity?.application)
        ViewModelProvider(this, NishthaOnlineLanguageViewModel.Factory(app))
            .get(NishthaOnlineLanguageViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingNishthaModule = DataBindingUtil.inflate(
            inflater, R.layout.nishtha_online_language_fragment, container, false
        )
        return bindingNishthaModule.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.title = getString(R.string.nishtha_nonline)
        initLanguageObserver()
        initModuleObserver()
        nishthaOnlineViewModel.getNishthaOnlineLanguages()
    }

    private fun initLanguageObserver() {
        nishthaOnlineViewModel.nishthaLanguageList.observe(
            viewLifecycleOwner,
            Observer<ArrayList<NishthaLanguageModel>> { t ->
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
            viewLifecycleOwner,
            Observer<ArrayList<NishthaModuleModel>> { t ->
                debugMyLists(t)

                val headerText = TextView(requireContext())
                headerText.text = when(t[0].modLang){
                    "en"->"English"
                    "hi"->"Hindi"
                    "ur"->"Urdu"
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
                headerText.setBackgroundColor(Color.parseColor("#999999"))

                val myRecyclerView = RecyclerView(requireContext())
                myRecyclerView.layoutManager = LinearLayoutManager(
                    requireContext(), RecyclerView.HORIZONTAL, false
                )
                myRecyclerView.adapter = ModuleAdapter(
                    context = requireContext(),
                    listOfModules = t,
                    onModuleClickedListener = this
                )

                bindingNishthaModule.llNishthaOnline.addView(headerText)
                bindingNishthaModule.llNishthaOnline.addView(myRecyclerView)
            }
        )
    }

    private fun debugMyLists(modules: ArrayList<NishthaModuleModel>) {
        for(m in modules)
            Logger.logDebug(m.modLang, m.modName)
    }

    override fun onModuleClicked(module: NishthaModuleModel) {

    }

}