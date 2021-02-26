package com.karan.nishtharedefined.ui.fragment.facetoface

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.const.AppConstants
import com.karan.nishtharedefined.databinding.FaceToFaceResourceFragmentBinding
import com.karan.nishtharedefined.model.facetoface.ModelResourceType
import com.karan.nishtharedefined.ui.activity.MainActivity
import com.karan.nishtharedefined.ui.activity.facetoface.FaceToFaceResourceViewModel
import com.karan.nishtharedefined.ui.fragment.fragmentsheets.DownloadBottomSheetFragment
import com.karan.nishtharedefined.utils.IntentUtils
import com.karan.nishtharedefined.utils.Logger

class FaceToFaceResourceFragment : Fragment() {

    private lateinit var bindingFaceToFaceResourceFragment: FaceToFaceResourceFragmentBinding
    private val faceToFaceResourceViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this, FaceToFaceResourceViewModel.Factory(activity.application))
            .get(FaceToFaceResourceViewModel::class.java)
    }
    private var resourcePairBundle: Pair<String, Pair<String, String>>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingFaceToFaceResourceFragment = DataBindingUtil.inflate(
            inflater,
            R.layout.face_to_face_resource_fragment,
            container,
            false
        )
        return bindingFaceToFaceResourceFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initResourcesObserver()
        receiveBundleData()
    }

    private fun receiveBundleData() {
        bindingFaceToFaceResourceFragment.pbFaceToFaceResourceProgress.visibility = View.VISIBLE
        val b = FaceToFaceResourceFragmentArgs.fromBundle(requireArguments())
        resourcePairBundle = b.resource as Pair<String, Pair<String, String>>
        faceToFaceResourceViewModel.getResources(
            resourcePairBundle!!.second.first, resourcePairBundle!!.second.second
        )
        setupToolbar(
            resourcePairBundle!!.first,
            resourcePairBundle!!.second.first
        )
    }

    private fun setupToolbar(modelName: String, language: String) {
        (activity as MainActivity).supportActionBar?.title = modelName
        (activity as MainActivity).supportActionBar?.subtitle = language
    }

    private fun initResourcesObserver() {
        faceToFaceResourceViewModel.resourceList.observe(
            viewLifecycleOwner,
            { t ->
                bindingFaceToFaceResourceFragment.pbFaceToFaceResourceProgress.visibility =
                    View.GONE
                if (t!!.isNotEmpty()) {
                    setResourcesVisibility(t[0])
                    initViewOnlineControls(t[0])
                    initDownloadControls(t[0])
                }
            })
    }


    private fun setResourcesVisibility(modelResource: ModelResourceType) {
        bindingFaceToFaceResourceFragment.cvTextResource.visibility =
            if (modelResource.res_d_text_url!!.isNotEmpty() && modelResource.res_v_text_url!!.isNotEmpty()) View.VISIBLE
            else View.GONE
        bindingFaceToFaceResourceFragment.cvVideoResource.visibility =
            if (modelResource.res_d_video_url!!.isNotEmpty() && modelResource.res_v_video_url!!.isNotEmpty()) View.VISIBLE
            else View.GONE
        bindingFaceToFaceResourceFragment.cvVideoPresentation.visibility =
            if (modelResource.res_d_present_url!!.isNotEmpty() && modelResource.res_d_present_url.isNotEmpty()) View.VISIBLE
            else View.GONE
    }

    private fun initViewOnlineControls(modelResource: ModelResourceType) {
        bindingFaceToFaceResourceFragment.rvTextViewOnline.setOnClickListener {
            IntentUtils.openPDF(requireContext(), modelResource.res_v_text_url)
        }
        bindingFaceToFaceResourceFragment.rvVideoViewOnline.setOnClickListener {
            IntentUtils.openYouTubeLink(requireContext(), modelResource.res_v_video_url)
        }
        bindingFaceToFaceResourceFragment.rvPresentationViewOnline.setOnClickListener {
            IntentUtils.openPresentation(requireContext(), modelResource.res_v_present_url)
        }
    }

    private fun initDownloadControls(modelResource: ModelResourceType) {
        Logger.logError("Text", modelResource.res_d_text_url!!)
        Logger.logError("Video", modelResource.res_d_video_url!!)
        Logger.logError("Presentation", modelResource.res_d_present_url!!)
        bindingFaceToFaceResourceFragment.rvTextDownload.setOnClickListener {
            DownloadBottomSheetFragment(
                (activity as MainActivity).supportActionBar?.title.toString(),
                (activity as MainActivity).supportActionBar?.subtitle.toString(),
                modelResource.res_d_text_url,
                activity?.application!!
            ).show(
                childFragmentManager,
                AppConstants.DOWNLOAD_SHEET_FRAGMENT_CONSTANT_TAG
            )
        }
        bindingFaceToFaceResourceFragment.rvVideoDownload.setOnClickListener {
            DownloadBottomSheetFragment(
                (activity as MainActivity).supportActionBar?.title.toString(),
                (activity as MainActivity).supportActionBar?.subtitle.toString(),
                modelResource.res_d_video_url,
                activity?.application!!
            ).show(
                childFragmentManager,
                AppConstants.DOWNLOAD_SHEET_FRAGMENT_CONSTANT_TAG
            )
        }
        bindingFaceToFaceResourceFragment.rvPresentationDownload.setOnClickListener {
            DownloadBottomSheetFragment(
                (activity as MainActivity).supportActionBar?.title.toString(),
                (activity as MainActivity).supportActionBar?.subtitle.toString(),
                modelResource.res_d_present_url,
                activity?.application!!
            ).show(
                childFragmentManager,
                AppConstants.DOWNLOAD_SHEET_FRAGMENT_CONSTANT_TAG
            )
        }
    }

}