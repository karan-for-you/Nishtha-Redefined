package com.karan.nishtharedefined.ui.fragment.nishthamodule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.databinding.ActivityNishthaOnlineModuleBinding
import com.karan.nishtharedefined.model.NishthaModuleModel
import com.karan.nishtharedefined.model.NishthaOnlineModuleDetail
import com.karan.nishtharedefined.ui.adapter.NishthaOnlineModuleAdapter

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
        resourcePairBundle = intent.getParcelableExtra("resource")
        initModuleObserver()
        initViews()
        makeModuleCall()
    }

    private fun initViews(){
        nishthaOnlineModuleBinding.tvNameOfNishthaModule.text = resourcePairBundle?.modName!!
        nishthaOnlineModuleBinding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun makeModuleCall(){
        nishthaOnlineModuleViewModel.getNishthaOnlineModuleList(
            resourcePairBundle?.modLang!!,
            resourcePairBundle?.modId!!
        )
    }

    private fun initModuleObserver() {
        nishthaOnlineModuleViewModel.nishthaOnlineModuleList.observe(
            this,
            Observer<ArrayList<NishthaOnlineModuleDetail>> { t ->
                if (!t.isNullOrEmpty()) {
                    nishthaOnlineModuleBinding.rvNishthaOnlineModules.layoutManager =
                        GridLayoutManager(this, 3)
                    nishthaOnlineModuleBinding.rvNishthaOnlineModules.adapter =
                        NishthaOnlineModuleAdapter(
                            this,
                            t,
                            this
                        )
                }
            }
        )
    }

    override fun onModuleResourceClicked(nishthaOnlineModuleDetail: NishthaOnlineModuleDetail) {
        // TODO: Handle appropriate action
    }


}