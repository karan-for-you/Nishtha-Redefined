package com.karan.nishtharedefined.ui.fragment.nishthamodule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.databinding.NishthaModuleFragmentBinding

class NishthaModuleFragment : Fragment(){

    private lateinit var bindingNishthaModule : NishthaModuleFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingNishthaModule = DataBindingUtil.inflate(
            inflater,R.layout.nishtha_module_fragment,container,false
        )
        return bindingNishthaModule.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initViews(){

    }

}