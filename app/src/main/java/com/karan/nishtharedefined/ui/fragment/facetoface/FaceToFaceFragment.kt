package com.karan.nishtharedefined.ui.fragment.facetoface

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.databinding.FaceToFaceFragmentBinding
import com.karan.nishtharedefined.model.ModelCategory
import com.karan.nishtharedefined.model.ModelCategoryModule
import com.karan.nishtharedefined.model.ModelLanguage
import com.karan.nishtharedefined.model.ModelResourceType
import com.karan.nishtharedefined.prefs.SessionPreferences
import com.karan.nishtharedefined.ui.activity.MainActivity
import com.karan.nishtharedefined.ui.activity.facetoface.FaceToFaceResourceActivity
import com.karan.nishtharedefined.ui.adapter.FaceToFaceCategoryAdapter
import com.karan.nishtharedefined.ui.adapter.FaceToFaceModuleAdapter
import com.karan.nishtharedefined.ui.fragment.LanguageBottomSheet

class FaceToFaceFragment : Fragment(),
    FaceToFaceCategoryAdapter.FaceToFaceCategoryListener,
    FaceToFaceModuleAdapter.OnFaceToFaceModuleClickListener,
    LanguageBottomSheet.OnLanguageSelectedListener {

    private lateinit var bindingFaceToFaceFragment: FaceToFaceFragmentBinding
    private lateinit var bottomSheet: LanguageBottomSheet
    private var selectedModuleName = ""
    private val faceToFaceViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this, FaceToFaceViewModel.Factory(activity.application))
            .get(FaceToFaceViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingFaceToFaceFragment = DataBindingUtil.inflate(
            inflater,
            R.layout.face_to_face_fragment,
            container,
            false
        )
        return bindingFaceToFaceFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //(activity as MainActivity).bindingMainActivity.toolbar.title = "Face to Face"
        initCategoryObserver()
        initModuleObserver()
        initLanguageObserver()
        makeCategoryCall()
    }

    private fun makeCategoryCall() {
        bindingFaceToFaceFragment.rvCategoryContent.layoutManager =
            LinearLayoutManager(requireContext())
        bindingFaceToFaceFragment.pbCategory.visibility = View.VISIBLE
        faceToFaceViewModel.getCategoryData(SessionPreferences.language)
    }


    private fun initCategoryObserver() {
        faceToFaceViewModel.categoryList.observe(viewLifecycleOwner,
            Observer<ArrayList<ModelCategory>> { t ->
                bindingFaceToFaceFragment.pbCategory.visibility = View.GONE
                if (t.isNotEmpty()) {
                    bindingFaceToFaceFragment.rvCategory.layoutManager =
                        GridLayoutManager(requireContext(), 2)
                    bindingFaceToFaceFragment.rvCategory.adapter = FaceToFaceCategoryAdapter(
                        context = requireContext(),
                        listOfModules = t,
                        faceToFaceCategoryListener = this
                    )
                    bindingFaceToFaceFragment.viewSeparator.visibility = View.VISIBLE
                }
            })
    }

    private fun initModuleObserver() {
        faceToFaceViewModel.moduleList.observe(viewLifecycleOwner,
            Observer<ArrayList<ModelCategoryModule>> { t ->
                bindingFaceToFaceFragment.pbModules.visibility = View.GONE
                if (t.isNotEmpty()) {
                    bindingFaceToFaceFragment.rvCategoryContent.adapter = FaceToFaceModuleAdapter(
                        context = requireContext(),
                        listOfModules = t,
                        faceToFaceModuleListener = this
                    )
                }
            })
    }

    private fun initLanguageObserver() {
        faceToFaceViewModel.languageList.observe(
            viewLifecycleOwner,
            Observer<ArrayList<ModelLanguage>> { t ->
                bindingFaceToFaceFragment.pbLanguage.visibility = View.GONE
                if (t.isNotEmpty()) {
                    bottomSheet = LanguageBottomSheet(
                        languageBottomSheetItemListener = this,
                        listOfLanguages = t,
                        moduleName = selectedModuleName
                    )
                    bottomSheet.show(
                        childFragmentManager,
                        "bottomSheet"
                    )
                }
            }
        )
    }

    override fun onFaceToFaceCategoryClicked(position: Int) {
        bindingFaceToFaceFragment.pbModules.visibility = View.VISIBLE
        faceToFaceViewModel.getCategoryModule(SessionPreferences.language, position.toString())
    }

    override fun onFaceToFaceModuleClicked(modelId: Int, moduleName: String) {
        selectedModuleName = moduleName
        bindingFaceToFaceFragment.pbLanguage.visibility = View.VISIBLE
        faceToFaceViewModel.getLanguages(modelId = modelId)
    }

    override fun onLanguageSelected(language: String, modId: String) {
        /*faceToFaceViewModel.getResources(
            lang = language,
            modelId = modId
        )
        bottomSheet.dismiss()
        findNavController().navigate(FaceToFaceFragmentDirections.actionFaceToFaceFragmentToFaceToFaceResourceFragment(Pair<>))*/
    }


}