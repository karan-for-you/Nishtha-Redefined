package com.karan.nishtharedefined.ui.fragment.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.databinding.LibraryFragmentBinding

class LibraryFragment : Fragment() {

    private lateinit var bindingLibraryFragment: LibraryFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         bindingLibraryFragment = DataBindingUtil.inflate(
            inflater,
            R.layout.library_fragment,
            container,
            false
        )
        return bindingLibraryFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initViews(){

    }


}