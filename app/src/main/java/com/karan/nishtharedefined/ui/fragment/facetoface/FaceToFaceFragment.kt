package com.karan.nishtharedefined.ui.fragment.facetoface

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.databinding.FaceToFaceFragmentBinding
import com.karan.nishtharedefined.model.ModelCategory
import com.karan.nishtharedefined.model.ModelCategoryModule
import com.karan.nishtharedefined.model.ModelLanguage
import com.karan.nishtharedefined.prefs.SessionPreferences
import com.karan.nishtharedefined.ui.adapter.FaceToFaceCategoryAdapter
import com.karan.nishtharedefined.ui.adapter.FaceToFaceModuleAdapter
import com.karan.nishtharedefined.ui.adapter.ModuleLanguageAdapter

class FaceToFaceFragment : Fragment(),
    FaceToFaceCategoryAdapter.FaceToFaceCategoryListener,
    FaceToFaceModuleAdapter.OnFaceToFaceModuleClickListener,
    ModuleLanguageAdapter.OnLanguageSelectedListener{

    private lateinit var bindingFaceToFaceFragment: FaceToFaceFragmentBinding
    private lateinit var languageBottomSheetLayout: BottomSheetBehavior<LinearLayout>
    private val faceToFaceViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this, FaceToFaceViewModel.Factory(activity.application))
            .get(FaceToFaceViewModel::class.java)
    }
    private var isBottomSheetReady = false

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
        initBottomSheetToggleView()
        initCategoryObserver()
        initModuleObserver()
        initLanguageObserver()
        setupBottomSheetBehaviour()
        makeCategoryCall()
    }

    private fun makeCategoryCall(){
        bindingFaceToFaceFragment.pbCategory.visibility = View.VISIBLE
        faceToFaceViewModel.getCategoryData(SessionPreferences.language)
    }

    private fun setupBottomSheetBehaviour() {
        languageBottomSheetLayout =
            BottomSheetBehavior.from(bindingFaceToFaceFragment.llBottomSheetView)
        languageBottomSheetLayout.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(p0: View, p1: Int) {
                // Code Review Note - Do Nothing
            }

            override fun onSlide(p0: View, p1: Float) {
                // Code Review Note - Do Nothing
            }
        })
    }

    private fun initBottomSheetToggleView() {
        bindingFaceToFaceFragment.tvHeader.setOnClickListener {
            if (isBottomSheetReady) {
                if (languageBottomSheetLayout.state == BottomSheetBehavior.STATE_COLLAPSED)
                    languageBottomSheetLayout.state = BottomSheetBehavior.STATE_EXPANDED
                else if (languageBottomSheetLayout.state == BottomSheetBehavior.STATE_EXPANDED)
                    languageBottomSheetLayout.state = BottomSheetBehavior.STATE_COLLAPSED
            }
            //languageBottomSheetLayout.state = BottomSheetBehavior.STATE_EXPANDED
        }
        bindingFaceToFaceFragment.rvCategory.setOnClickListener {
            if (languageBottomSheetLayout.state == BottomSheetBehavior.STATE_EXPANDED)
                languageBottomSheetLayout.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        bindingFaceToFaceFragment.rvCategoryContent.setOnClickListener {
            if (languageBottomSheetLayout.state == BottomSheetBehavior.STATE_EXPANDED)
                languageBottomSheetLayout.state = BottomSheetBehavior.STATE_COLLAPSED
        }
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
                    bindingFaceToFaceFragment.rvLanguages.adapter = ModuleLanguageAdapter(
                        context = requireContext(),
                        languageList = t,
                        onLanguageSelectedListener = this
                    )
                }
            }
        )
    }

    override fun onFaceToFaceCategoryClicked(position: Int) {
        bindingFaceToFaceFragment.pbModules.visibility = View.VISIBLE
        faceToFaceViewModel.getCategoryModule(SessionPreferences.language, position.toString())
    }

    override fun onFaceToFaceModuleClicked(modelId: Int) {
        bindingFaceToFaceFragment.pbLanguage.visibility = View.VISIBLE
        faceToFaceViewModel.getLanguages(modelId = modelId)
    }

    override fun onLanguageSelected(language: String) {
        // TODO: Navigate to a different UI and show stuff
    }
}