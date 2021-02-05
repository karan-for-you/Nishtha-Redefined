package com.karan.nishtharedefined.ui.fragment.fragmentsheets

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.databinding.DownloadBottomSheetBinding
import com.karan.nishtharedefined.ui.activity.facetoface.FaceToFaceResourceViewModel
import okhttp3.ResponseBody

class DownloadBottomSheetFragment(
    var downloadLink : String,
    var mContext : Application
) : BottomSheetDialogFragment() {

    private lateinit var bindingDownloadBottomSheetDialogFragment: DownloadBottomSheetBinding
    private val downloadBottomSheetViewModel by lazy {
        ViewModelProvider(
            this,
            DownloadBottomSheetViewModel.Factory(mContext)
        ).get(DownloadBottomSheetViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        initDownloadObserver()
        downloadBottomSheetViewModel.getResource(downloadLink)

    }

    private fun initDownloadObserver(){
        downloadBottomSheetViewModel.downloadBody.observe(
            this,
            Observer<ResponseBody> { t ->
                if(t!=null){
                    // TODO: ResponseBody has been received, make it a file and start downloading
                }else{
                    /// TODO: Show the error message and ask the user to try again later
                }
            }
        )
    }


}