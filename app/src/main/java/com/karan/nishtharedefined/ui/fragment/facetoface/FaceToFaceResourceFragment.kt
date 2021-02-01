package com.karan.nishtharedefined.ui.fragment.facetoface

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.databinding.FaceToFaceResourceFragmentBinding

class FaceToFaceResourceFragment : Fragment() {

    private lateinit var bindingFaceToFaceFragment : FaceToFaceResourceFragmentBinding
    private val faceToFaceResourceViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(
            this,
            FaceToFaceResourceViewModel.Factory(activity.application))
            .get(FaceToFaceResourceViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingFaceToFaceFragment = DataBindingUtil.inflate(
            inflater,
            R.layout.face_to_face_resource_fragment,
            container,
            false
        )
        return bindingFaceToFaceFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        receiveBundleData()
    }

    fun receiveBundleData(){
        val resource = FaceToFaceResourceFragmentArgs.fromBundle(
            requireArguments()
        ).resource

    }


}