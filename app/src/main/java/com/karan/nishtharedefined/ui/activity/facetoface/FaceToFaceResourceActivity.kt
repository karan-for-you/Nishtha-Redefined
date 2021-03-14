package com.karan.nishtharedefined.ui.activity.facetoface

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.databinding.ActivityFaceToFaceResourceBinding
import com.karan.nishtharedefined.model.facetoface.ModelResourceType

class FaceToFaceResourceActivity : AppCompatActivity() {

    private lateinit var bindingFaceToFaceResourceActivity :
            ActivityFaceToFaceResourceBinding
    private val faceToFaceResourceViewModel by lazy {
        ViewModelProvider(
            this,
            FaceToFaceResourceViewModel.Factory(this.application)
        ).get(FaceToFaceResourceViewModel::class.java)
    }
    private var resource: ModelResourceType? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingFaceToFaceResourceActivity = DataBindingUtil.setContentView(
            this,
            R.layout.activity_face_to_face_resource
        )
        receiveBundleData()
        setResources()
        initControls()
    }

    private fun receiveBundleData() {
        resource = intent.extras?.get("resource") as ModelResourceType?
        bindingFaceToFaceResourceActivity.tvModuleName.text = intent?.extras?.getString("moduleName")
    }

    private fun setResources() {
        bindingFaceToFaceResourceActivity.cvTextResource.visibility =
            if (resource?.res_d_text_url!!.isNotEmpty() && resource?.res_v_text_url!!.isNotEmpty()) View.VISIBLE
            else View.GONE
        bindingFaceToFaceResourceActivity.cvVideoResource.visibility =
            if (resource?.res_d_video_url!!.isNotEmpty() && resource?.res_v_video_url!!.isNotEmpty()) View.VISIBLE
            else View.GONE
        bindingFaceToFaceResourceActivity.cvVideoPresentation.visibility =
            if (resource?.res_d_present_url!!.isNotEmpty() && resource?.res_d_present_url!!.isNotEmpty()) View.VISIBLE
            else View.GONE
    }

    private fun initControls(){
        bindingFaceToFaceResourceActivity.rvTextViewOnline.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(resource?.res_d_text_url)))
        }
        bindingFaceToFaceResourceActivity.rvVideoViewOnline.setOnClickListener {
            val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:${resource?.res_v_video_url}"))
            appIntent.putExtra("force_fullscreen", true)
            val webIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=$resource?.res_v_video_url"))

            try {
                val title = this.resources?.getString(R.string.chooser_title)
                val chooser = Intent.createChooser(appIntent, title)
                this.startActivity(chooser)
            } catch (ex: ActivityNotFoundException) {
                this.startActivity(webIntent)
            }
        }


    }

}