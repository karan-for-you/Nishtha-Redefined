package com.karan.nishtharedefined.ui.fragment.facetoface

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.databinding.FaceToFaceResourceFragmentBinding
import com.karan.nishtharedefined.model.ModelResourceType
import com.karan.nishtharedefined.ui.activity.facetoface.FaceToFaceResourceViewModel

class FaceToFaceResourceFragment : Fragment() {

    private lateinit var bindingFaceToFaceResourceFragment: FaceToFaceResourceFragmentBinding
    private val faceToFaceResourceViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this, FaceToFaceResourceViewModel.Factory(activity.application))
            .get(FaceToFaceResourceViewModel::class.java)
    }
    private var resourcePairBundle = Pair("","")

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
        resourcePairBundle = b.resource as Pair<String, String>
        faceToFaceResourceViewModel.getResources(
            resourcePairBundle.first, resourcePairBundle.second
        )
    }

    private fun initResourcesObserver(){
        faceToFaceResourceViewModel.resourceList.observe(
            viewLifecycleOwner,
            Observer<ArrayList<ModelResourceType>> { t ->
                bindingFaceToFaceResourceFragment.pbFaceToFaceResourceProgress.visibility = View.GONE
                if(t!!.isNotEmpty()){
                    setResources(t[0])
                    initControls(t[0])
                }
            })
    }


    private fun setResources(modelResource : ModelResourceType) {
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

    private fun initControls(modelResource : ModelResourceType){
        bindingFaceToFaceResourceFragment.rvTextViewOnline.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(modelResource.res_d_text_url)))
        }
        bindingFaceToFaceResourceFragment.rvVideoViewOnline.setOnClickListener {
            val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:${modelResource.res_v_video_url}"))
            appIntent.putExtra("force_fullscreen", true)
            val webIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=$modelResource?.res_v_video_url"))

            try {
                val title = this.resources.getString(R.string.chooser_title)
                val chooser = Intent.createChooser(appIntent, title)
                this.startActivity(chooser)
            } catch (ex: ActivityNotFoundException) {
                this.startActivity(webIntent)
            }
        }
    }

}