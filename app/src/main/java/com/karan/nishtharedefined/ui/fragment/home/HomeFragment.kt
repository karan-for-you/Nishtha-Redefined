package com.karan.nishtharedefined.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.databinding.HomeFragmentBinding
import com.karan.nishtharedefined.ui.adapter.HomeAdapter

class HomeFragment : Fragment() {

    private lateinit var bindingHomeFragment : HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingHomeFragment = DataBindingUtil.inflate(
            inflater,
            R.layout.home_fragment,
            container,
            false
        )
        return bindingHomeFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews(){

    }

    private fun initHomeRecyclerView(){

        bindingHomeFragment.rvMainMenu.apply {
            layoutManager = GridLayoutManager(requireContext(),2)
            //adapter = HomeAdapter()
        }
    }

}