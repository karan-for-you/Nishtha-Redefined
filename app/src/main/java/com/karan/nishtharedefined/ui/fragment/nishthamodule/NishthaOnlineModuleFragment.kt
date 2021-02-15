package com.karan.nishtharedefined.ui.fragment.nishthamodule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.databinding.NishthaOnlineModuleFragmentBinding
import com.karan.nishtharedefined.model.NishthaOnlineModuleDetail

class NishthaOnlineModuleFragment : Fragment() {

    private lateinit var bindingNishthaOnlineModuleFragment: NishthaOnlineModuleFragmentBinding
    private val nishthaOnlineModuleViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this, NishthaOnlineModuleViewModel.Factory(activity.application))
            .get(NishthaOnlineModuleViewModel::class.java)
    }
    private var resourcePairBundle: Pair<String, Pair<String, String>>? = null
    //private var moduleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingNishthaOnlineModuleFragment = DataBindingUtil.inflate(
            inflater,
            R.layout.nishtha_online_module_fragment,
            container,
            false
        )
        return bindingNishthaOnlineModuleFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initModuleObserver()
        receiveBundleData()
    }

    private fun receiveBundleData(){
        val b = NishthaOnlineModuleFragmentArgs.fromBundle(requireArguments())
        resourcePairBundle = b.resourceModule as Pair<String, Pair<String, String>>

        bindingNishthaOnlineModuleFragment.tvNameOfNishthaModule.text =
            resourcePairBundle!!.first

        nishthaOnlineModuleViewModel.getNishthaOnlineModuleList(
            resourcePairBundle!!.second.first,
            resourcePairBundle!!.second.second
        )
    }

    private fun initModuleObserver(){
        nishthaOnlineModuleViewModel.nishthaOnlineModuleList.observe(
            viewLifecycleOwner,
            Observer<ArrayList<NishthaOnlineModuleDetail>> { t ->
                if(t.isNullOrEmpty()){

                }
            }
        )
    }

}