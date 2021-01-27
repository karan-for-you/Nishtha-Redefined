package com.karan.nishtharedefined.ui.fragment.facetoface

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.databinding.FaceToFaceFragmentBinding
import com.karan.nishtharedefined.model.ModelCategory
import com.karan.nishtharedefined.model.ModelCategoryModule
import com.karan.nishtharedefined.prefs.SessionPreferences
import com.karan.nishtharedefined.ui.adapter.FaceToFaceCategoryAdapter

class FaceToFaceFragment : Fragment(), FaceToFaceCategoryAdapter.FaceToFaceCategoryListener {

    private lateinit var bindingFaceToFaceFragment: FaceToFaceFragmentBinding
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
        bindingFaceToFaceFragment.rvCategory.layoutManager =
            GridLayoutManager(requireContext(), 2)
        initCategoryObserver()
        initModuleObserver()
        faceToFaceViewModel.getCategoryData(SessionPreferences.language)
    }

    private fun initCategoryObserver() {
        faceToFaceViewModel.categoryList.observe(viewLifecycleOwner,
            Observer<ArrayList<ModelCategory>> { t ->
                bindingFaceToFaceFragment.pbCategory.visibility = View.GONE
                bindingFaceToFaceFragment.viewSeparator.visibility = View.VISIBLE
                if(t.isNotEmpty())
                    bindingFaceToFaceFragment.rvCategory.adapter = FaceToFaceCategoryAdapter(
                        requireContext(), t, this
                    )
            })
    }

    private fun initModuleObserver(){
        faceToFaceViewModel.moduleList.observe(viewLifecycleOwner,
            Observer<ArrayList<ModelCategoryModule>> { t ->
                if(t.isNullOrEmpty()){

                }
            })
    }

    override fun onFaceToFaceCategoryClicked(position: Int) {
        faceToFaceViewModel.getCategoryModule(SessionPreferences.language,position.toString())
    }
}