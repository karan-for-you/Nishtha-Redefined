package com.karan.nishtharedefined.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.databinding.DownloadBottomSheetBinding

class DownloadBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var bindingDownloadBottomSheetDialogFragment: DownloadBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         bindingDownloadBottomSheetDialogFragment = DataBindingUtil.inflate(
            inflater,
            R.layout.download_bottom_sheet,
            container,
            false
        )
        return bindingDownloadBottomSheetDialogFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}