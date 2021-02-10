package com.karan.nishtharedefined.ui.fragment.facetoface

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.databinding.FaceToFaceResourceFragmentBinding
import com.karan.nishtharedefined.model.ModelResourceType
import com.karan.nishtharedefined.ui.activity.MainActivity
import com.karan.nishtharedefined.ui.activity.facetoface.FaceToFaceResourceViewModel
import com.karan.nishtharedefined.ui.fragment.fragmentsheets.DownloadBottomSheetFragment
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
            Observer<ArrayList<ModelResourceType>> { t ->
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
            startActivity(
                Intent
                    (
                    Intent.ACTION_VIEW,
                    Uri.parse(modelResource.res_d_text_url)
                )
            )
        }
        bindingFaceToFaceResourceFragment.rvVideoViewOnline.setOnClickListener {
            val appIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("vnd.youtube:${modelResource.res_v_video_url}")
            )
            appIntent.putExtra("force_fullscreen", true)
            val webIntent =
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/watch?v=$modelResource?.res_v_video_url")
                )

            try {
                val title = this.resources.getString(R.string.chooser_title)
                val chooser = Intent.createChooser(appIntent, title)
                this.startActivity(chooser)
            } catch (ex: ActivityNotFoundException) {
                Logger.logError("Error opening Presentation", ex.localizedMessage!!.toString())
                this.startActivity(webIntent)
            }
        }
        bindingFaceToFaceResourceFragment.rvPresentationViewOnline.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(modelResource.res_v_present_url)
            try {
                val title = context?.resources?.getString(R.string.chooser_title)
                val chooser = Intent.createChooser(i, title)
                context?.startActivity(chooser)
            } catch (e: Exception) {
                Logger.logError("Error opening Presentation", e.localizedMessage!!.toString())
                Toast.makeText(context, "Can Not Open link", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initDownloadControls(modelResource: ModelResourceType) {
        Logger.logError("Text",modelResource.res_d_text_url!!)
        Logger.logError("Video",modelResource.res_d_video_url!!)
        Logger.logError("Presentation",modelResource.res_d_present_url!!)
        bindingFaceToFaceResourceFragment.rvTextDownload.setOnClickListener {
            DownloadBottomSheetFragment(
                (activity as MainActivity).supportActionBar?.title.toString(),
                (activity as MainActivity).supportActionBar?.subtitle.toString(),
                modelResource.res_d_text_url,
                activity?.application!!).show(
                childFragmentManager,"downloadBottomSheet"
            )
        }
        bindingFaceToFaceResourceFragment.rvVideoDownload.setOnClickListener {
            DownloadBottomSheetFragment(
                (activity as MainActivity).supportActionBar?.title.toString(),
                (activity as MainActivity).supportActionBar?.subtitle.toString(),
                modelResource.res_d_video_url,
                activity?.application!!
            ).show(
                childFragmentManager,"downloadBottomSheet"
            )
        }
        bindingFaceToFaceResourceFragment.rvPresentationDownload.setOnClickListener {
            DownloadBottomSheetFragment(
                (activity as MainActivity).supportActionBar?.title.toString(),
                (activity as MainActivity).supportActionBar?.subtitle.toString(),
                modelResource.res_d_present_url,
                activity?.application!!
            ).show(
                childFragmentManager,"downloadBottomSheet"
            )
        }
    }

}