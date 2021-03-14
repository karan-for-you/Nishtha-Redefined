package com.karan.nishtharedefined.ui.fragment.fragmentsheets

import android.app.Application
import androidx.lifecycle.*
import com.karan.nishtharedefined.networking.ServiceBuilder
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import retrofit2.Callback
import retrofit2.Response
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream

class DownloadBottomSheetViewModel(
    app : Application
) : AndroidViewModel(
    app
)  {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _downloadBody = MutableLiveData<ResponseBody?>()
    val downloadBody : LiveData<ResponseBody?>
        get() = _downloadBody


    fun getResource(link : String){
        uiScope.launch {
            val downloadService = ServiceBuilder.retrofitService.getResource(link)
            downloadService.enqueue(downloadCallBack)
        }
    }

    suspend fun saveFileToDisk(responseBody : ResponseBody, pathToSave : String){
        withContext(Dispatchers.IO){
            if(responseBody!=null){
                var inputStream : InputStream? = null
                try{
                    inputStream = responseBody.byteStream()
                    val fileInputStream = FileOutputStream(pathToSave)
                    fileInputStream.use {

                    }
                }catch (e : Exception){

                }
            }
        }
    }

    fun cancelGettingResource(){
        //uiScope.cancel(null)
    }

    private val downloadCallBack = object : Callback<ResponseBody>{
        override fun onResponse(
            call: retrofit2.Call<ResponseBody>,
            response: Response<ResponseBody>
        ) {
            _downloadBody.value = response.body()
        }

        override fun onFailure(call: retrofit2.Call<ResponseBody>, t: Throwable) {
            _downloadBody.value = null
        }

    }


    @Suppress("UNCHECKED_CAST")
    class Factory(val app : Application) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(DownloadBottomSheetViewModel::class.java))
                return DownloadBottomSheetViewModel(app) as T
            throw IllegalAccessException("Can't create Download View Model")
        }
    }

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }
}