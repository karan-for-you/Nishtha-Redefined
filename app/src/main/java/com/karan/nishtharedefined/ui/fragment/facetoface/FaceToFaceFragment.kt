package com.karan.nishtharedefined.ui.fragment.facetoface

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.databinding.FaceToFaceFragmentBinding
import com.karan.nishtharedefined.model.ModelCategory
import com.karan.nishtharedefined.model.ModelCategoryModule
import com.karan.nishtharedefined.model.ModelLanguage
import com.karan.nishtharedefined.prefs.SessionPreferences
import com.karan.nishtharedefined.ui.activity.MainActivity
import com.karan.nishtharedefined.ui.adapter.FaceToFaceCategoryAdapter
import com.karan.nishtharedefined.ui.adapter.FaceToFaceModuleAdapter
import com.karan.nishtharedefined.ui.fragment.fragmentsheets.LanguageBottomSheetFragment

class FaceToFaceFragment : Fragment(),
    FaceToFaceCategoryAdapter.FaceToFaceCategoryListener,
    FaceToFaceModuleAdapter.OnFaceToFaceModuleClickListener,
    LanguageBottomSheetFragment.OnLanguageSelectedListener {

    private lateinit var bindingFaceToFaceFragment: FaceToFaceFragmentBinding
    private lateinit var bottomSheetFragment: LanguageBottomSheetFragment
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
        initCategoryObserver()
        initModuleObserver()
        initLanguageObserver()
        makeCategoryCall()
    }

    private fun makeCategoryCall() {
        bindingFaceToFaceFragment.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
        bindingFaceToFaceFragment.pbFaceToFace.visibility = View.VISIBLE
        faceToFaceViewModel.getCategoryData(SessionPreferences.language)
    }


    private fun initCategoryObserver() {
        faceToFaceViewModel.categoryList.observe(viewLifecycleOwner,
            Observer<ArrayList<ModelCategory>> { t ->
                bindingFaceToFaceFragment.pbFaceToFace.visibility = View.GONE
                if (t.isNotEmpty()) {
                    bindingFaceToFaceFragment.rvCategory.layoutManager =
                        GridLayoutManager(requireContext(), 2)
                    bindingFaceToFaceFragment.rvCategory.adapter = FaceToFaceCategoryAdapter(
                        context = requireContext(),
                        listOfModules = t,
                        faceToFaceCategoryListener = this
                    )

                }
            })
    }

    private fun initModuleObserver() {
        faceToFaceViewModel.moduleList.observe(viewLifecycleOwner,
            Observer<ArrayList<ModelCategoryModule>> { t ->
                bindingFaceToFaceFragment.pbFaceToFace.visibility = View.GONE
                if (t.isNotEmpty()) {
                    bindingFaceToFaceFragment.rvModules.visibility = View.VISIBLE
                    bindingFaceToFaceFragment.tvFilterModule.visibility = View.VISIBLE
                    bindingFaceToFaceFragment.rvModules.adapter = FaceToFaceModuleAdapter(
                        context = requireContext(),
                        listOfModules = t,
                        faceToFaceModuleListener = this
                    )
                }

            })
    }

    // Recreation working in our favour
    private fun initLanguageObserver() {
        faceToFaceViewModel.languageList.observe(
            viewLifecycleOwner,
            Observer<ArrayList<ModelLanguage>> { t ->
                bindingFaceToFaceFragment.pbFaceToFace.visibility = View.GONE
                if (t.isNotEmpty()) {
                    bottomSheetFragment = LanguageBottomSheetFragment(
                        languageBottomSheetItemListener = this,
                        listOfLanguages = t,
                        moduleName = selectedModuleName
                    )
                    bottomSheetFragment.show(
                        childFragmentManager,
                        "bottomSheet"
                    )
                }
            }
        )
    }

    override fun onFaceToFaceCategoryClicked(position: Int) {
        bindingFaceToFaceFragment.pbFaceToFace.visibility = View.VISIBLE
        bindingFaceToFaceFragment.rvModules.visibility = View.GONE
        faceToFaceViewModel.getCategoryModule(SessionPreferences.language, position.toString())
    }

    override fun onFaceToFaceModuleClicked(modelId: Int, moduleName: String) {
        selectedModuleName = moduleName
        bindingFaceToFaceFragment.pbFaceToFace.visibility = View.VISIBLE
        faceToFaceViewModel.getLanguages(modelId = modelId)
    }

    override fun onLanguageSelected(language: String, modId: String) {
        bottomSheetFragment.dismiss()
        findNavController().navigate(
            FaceToFaceFragmentDirections.actionFaceToFaceFragmentToFaceToFaceResourceFragment(
                Pair(selectedModuleName,Pair(language, modId))
            )
        )
    }


}