package com.karan.nishtharedefined.ui.fragment.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.databinding.LibraryFragmentBinding
import com.karan.nishtharedefined.ui.activity.MainActivity
import com.karan.nishtharedefined.ui.adapter.LibraryAdapter
import com.karan.nishtharedefined.ui.fragment.facetoface.FaceToFaceViewModel
import com.karan.nishtharedefined.utils.AppUtils
import java.io.File

class LibraryFragment : Fragment() {

    private lateinit var bindingLibraryFragment: LibraryFragmentBinding
    private val libraryViewModel by lazy {
        val activity = requireNotNull(activity?.application)
        ViewModelProvider(this, LibraryViewModel.Factory(activity))
            .get(LibraryViewModel::class.java)
    }
    private var listOfFiles = ArrayList<Pair<String,String>>()

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
        (activity as MainActivity).supportActionBar?.title =
            getString(R.string.home_screen_library)
        initViews()

    }

    private fun initViews() {
       bindingLibraryFragment.rvLibraryItems.layoutManager =
           LinearLayoutManager(requireContext())
        bindingLibraryFragment.rvLibraryItems.adapter = LibraryAdapter(
            listOfFiles,requireContext()
        )
    }

    private fun initObserver(){

    }




}