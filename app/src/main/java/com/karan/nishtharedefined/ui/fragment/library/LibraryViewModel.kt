package com.karan.nishtharedefined.ui.fragment.library

import android.app.Application
import androidx.lifecycle.*
import com.karan.nishtharedefined.utils.IOUtils
import java.io.File

class LibraryViewModel(var app: Application) : AndroidViewModel(app) {


    private var _directoryList = MutableLiveData<ArrayList<Pair<String?,String?>>>()
    val directoryList : LiveData<ArrayList<Pair<String?,String?>>>
        get() = _directoryList

    fun getDirectoryItems(){
        val transferableList = ArrayList<Pair<String?,String?>>()
        val listOfExtensions = ArrayList<String?>()

        val arrayOfFiles  =
            File(IOUtils.getFilteredPath(app.applicationContext)).listFiles()
        val listOfFiles: ArrayList<File> = arrayOfFiles.toCollection(ArrayList())

        for (l in listOfFiles)
            listOfExtensions.add(IOUtils.fileExt(l.name))

        for(i in 0 until listOfFiles.size)
            transferableList.add(Pair(listOfFiles[i].name,listOfExtensions[i]))

        _directoryList.value = transferableList
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LibraryViewModel::class.java))
                return LibraryViewModel(app) as T
            throw IllegalAccessException("Can't create Library View Model")
        }

    }
}