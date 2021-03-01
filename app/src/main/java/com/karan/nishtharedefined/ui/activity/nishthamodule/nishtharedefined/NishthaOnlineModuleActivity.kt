package com.karan.nishtharedefined.ui.activity.nishthamodule.nishtharedefined

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.const.AppConstants
import com.karan.nishtharedefined.databinding.ActivityNishthaOnlineModuleBinding
import com.karan.nishtharedefined.model.nishthaonline.NishthaModuleModel
import com.karan.nishtharedefined.model.nishthaonline.NishthaOnlineModuleDetail
import com.karan.nishtharedefined.ui.adapter.NishthaOnlineModuleAdapter
import com.karan.nishtharedefined.utils.IntentUtils

class NishthaOnlineModuleActivity : AppCompatActivity(),
    NishthaOnlineModuleAdapter.OnModuleResourceClickListener {

    private lateinit var nishthaOnlineModuleBinding: ActivityNishthaOnlineModuleBinding
    private val nishthaOnlineModuleViewModel by lazy {
        ViewModelProvider(this, NishthaOnlineModuleViewModel.Factory(application))
            .get(NishthaOnlineModuleViewModel::class.java)
    }
    private var resourcePairBundle: NishthaModuleModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nishthaOnlineModuleBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_nishtha_online_module
        )
        resourcePairBundle = intent.getParcelableExtra(AppConstants.NISHTHA_ONLINE_BUNDLE)
        initModuleObserver()
        initViews()
        makeModuleCall()
    }

    private fun initViews() {
        nishthaOnlineModuleBinding.tvNameOfNishthaModule.text = resourcePairBundle?.modName!!
        nishthaOnlineModuleBinding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun makeModuleCall() {
        nishthaOnlineModuleViewModel.getNishthaOnlineModuleList(
            resourcePairBundle?.modLang!!,
            resourcePairBundle?.modId!!
        )
    }

    private fun initModuleObserver() {
        nishthaOnlineModuleViewModel.nishthaOnlineModuleList.observe(
            this,
            { t ->
                if (!t.isNullOrEmpty()) {
                    nishthaOnlineModuleBinding.tvCount.text = resourcePairBundle?.modLang+"-"+t.size.toString()
                    nishthaOnlineModuleBinding.rvNishthaOnlineModules.layoutManager =
                        GridLayoutManager(this, 3)
                    nishthaOnlineModuleBinding.rvNishthaOnlineModules.adapter =
                        NishthaOnlineModuleAdapter(
                            context = this,
                            listOfResources = t,
                            onModuleResourceClickListener = this
                        )
                }
            }
        )
    }

    override fun onModuleResourceClicked(nishthaOnlineModuleDetail: NishthaOnlineModuleDetail) {
        when (nishthaOnlineModuleDetail.resource__type) {
            AppConstants.RES_TEXT -> IntentUtils.openPDF(
                context = this,
                linkToOpen = nishthaOnlineModuleDetail.resource__link
            )
            AppConstants.RES_VIDEO -> IntentUtils.openYouTubeLink(
                context = this,
                linkToPlay = nishthaOnlineModuleDetail.resource__link
            )
        }
    }


}