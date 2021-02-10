package com.karan.nishtharedefined.ui.fragment.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.databinding.LibraryFragmentBinding
import com.karan.nishtharedefined.ui.activity.MainActivity
import com.karan.nishtharedefined.ui.adapter.LibraryAdapter

class LibraryFragment : Fragment(), LibraryAdapter.OnLibraryItemClickListener {

    private lateinit var bindingLibraryFragment: LibraryFragmentBinding
    private lateinit var myLibraryAdapter: LibraryAdapter
    private val libraryViewModel by lazy {
        val activity = requireNotNull(activity?.application)
        ViewModelProvider(this, LibraryViewModel.Factory(activity))
            .get(LibraryViewModel::class.java)
    }

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
        initDirectoryObserver()
        libraryViewModel.getDirectoryItems()
    }


    private fun initDirectoryObserver() {
        libraryViewModel.directoryList.observe(
            viewLifecycleOwner,
            Observer<ArrayList<Pair<String?, String?>>> { t ->
                if (t!!.isNotEmpty()) {
                    bindingLibraryFragment.rvLibraryItems.layoutManager =
                        LinearLayoutManager(requireContext())
                    myLibraryAdapter = LibraryAdapter(
                        t,
                        requireContext(),
                        onLibraryItemClickListener = this
                    )
                    bindingLibraryFragment.rvLibraryItems.adapter = myLibraryAdapter
                }
            }
        )
    }

    override fun onLibraryItemClicked(pair: Pair<String?, String?>) {

    }


}