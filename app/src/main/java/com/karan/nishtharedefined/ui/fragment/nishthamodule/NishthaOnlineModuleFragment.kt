package com.karan.nishtharedefined.ui.fragment.nishthamodule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.databinding.NishthaOnlineModuleFragmentBinding

class NishthaOnlineModuleFragment : Fragment() {

    private lateinit var bindingNishthaOnlineModuleFragment: NishthaOnlineModuleFragmentBinding

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
    }

    private fun receiveBundleData(){

    }

    private fun getNishthaOnlineModuleDetail(){

    }

}