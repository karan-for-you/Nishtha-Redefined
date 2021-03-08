package com.karan.nishtharedefined.ui.fragment.fragmentsheets

import android.annotation.SuppressLint
import android.app.Application
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
import okhttp3.ResponseBody

class DownloadBottomSheetFragment(
    var nameOfModule: String,
    var language : String,
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

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingDownloadBottomSheetDialogFragment.tvFileDetails.text =
            "$nameOfModule - $language"
        initDownloadObserver()
        downloadBottomSheetViewModel.getResource(downloadLink)

        bindingDownloadBottomSheetDialogFragment.tvDone.setOnClickListener {
            downloadBottomSheetViewModel.cancelGettingResource()
        }
    }

    private fun initDownloadObserver(){
        downloadBottomSheetViewModel.downloadBody.observe(
            this,
            { t ->
                /*if(t!=null){
                    // TODO: ResponseBody has been received, make it a file and start downloading
                    // TODO: Use Co-routines to accommodate file in the system
                }else{
                    /// TODO: Show the error message and ask the user to try again later
                }*/
            }
        )
    }

    /*class DownloadFile : AsyncTask<ResponseBody, Pair<Int, Long>, String>() {
        override fun doInBackground(vararg params: ResponseBody?): String {

        }

        override fun onProgressUpdate(vararg progress: Pair<Int, Long>) {
            Timber.d(progress[0].second.toString())

            if (progress[0].first == 100) {
                Timber.i("File downloaded successfully")
                when (resType) {
                    PRESENT_RESOURCE_TYPE -> {
                        faceToFaceViewModel._fileDownloadstatusPRESENT.value = true
                    }
                    VIDEO_RESOURCE_TYPE -> {
                        faceToFaceViewModel._fileDownloadstatusVIDEO.value = true
                    }
                    PDF_RESOURCE_TYPE -> {
                        faceToFaceViewModel._fileDownloadstatusPDF.value = true
                    }
                }
                dialog.dismiss()
            }

            if (progress[0].second > 0) {
                val currentProgress =
                    (progress[0].first.toDouble() / progress[0].second.toDouble() * 100).toInt()

                dialog.findViewById<DonutProgress>(R.id.file_download_progress)
                    .setDonut_progress(currentProgress.toString())

                Timber.i("Progress $currentProgress%")
            }

            if (progress[0].first == -1) {
                Timber.i("Download failed")
                dialog.dismiss()
            }
        }

        private fun updateProgress(progressDetails: Pair<Int, Long>) {
            publishProgress(progressDetails)
        }

        override fun onPostExecute(result: String?) {}

        fun saveToDisk(body: ResponseBody,
                       filename: String) {
            try {
                val destinationFile = File(
                    Environment.getExternalStorageDirectory().absolutePath + "/NISHTHA/", filename
                )

                var inputStream: InputStream? = null
                var outputStream: OutputStream? = null
                try {
                    inputStream = body.byteStream()
                    outputStream = FileOutputStream(destinationFile)
                    val data = ByteArray(4096)
                    var count: Int = 0
                    var progress = 0
                    val fileSize = body.contentLength()
                    //Timber.d("File Size=$fileSize")
                    while (inputStream.read(data).also({ count = it }) != -1) {
                        outputStream.write(data, 0, count)
                        progress += count
                        val pairs = Pair(progress, fileSize)
                        updateProgress(pairs)
                        //Timber.d("Progress: $progress/$fileSize >>>> ${progress.toFloat() / fileSize}")
                    }
                    outputStream.flush()
                    val pairs = Pair(100, 100L)
                    updateProgress(pairs)
                    return
                } catch (e: IOException) {
                    e.printStackTrace()
                    val pairs = Pair(-1, java.lang.Long.valueOf(-1))
                    updateProgress(pairs)
                    //Timber.d("Failed to save the file!")
                    return
                } finally {
                    inputStream?.close()
                    outputStream?.close()
                }
            } catch (e: IOException) {
                e.printStackTrace()
                //Timber.d("Failed to save the file!")
                return
            }
        }

    }*/


}