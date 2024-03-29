package com.karan.nishtharedefined.ui.fragment.fragmentsheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.databinding.LanguageBottomSheetBinding
import com.karan.nishtharedefined.model.facetoface.ModelLanguage
import com.karan.nishtharedefined.ui.adapter.ModuleLanguageAdapter

class LanguageBottomSheetFragment(
    private val languageBottomSheetItemListener: OnLanguageSelectedListener,
    private val listOfLanguages: ArrayList<ModelLanguage>,
    private val moduleName: String
) : BottomSheetDialogFragment(),
    ModuleLanguageAdapter.OnLanguageSelectedListener {

    private lateinit var bindingBottomSheetFragment: LanguageBottomSheetBinding
    private var onLanguageSelectedListener: ModuleLanguageAdapter.OnLanguageSelectedListener = this

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingBottomSheetFragment = DataBindingUtil.inflate(
            inflater,
            R.layout.language_bottom_sheet,
            container,
            false
        )
        return bindingBottomSheetFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //bindingBottomSheetFragment.tvModuleName.text = moduleName
        bindingBottomSheetFragment.rvLanguages.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ModuleLanguageAdapter(
                context = requireContext(),
                languageList = listOfLanguages,
                onLanguageSelectedListener = onLanguageSelectedListener
            )

        }
    }

    interface OnLanguageSelectedListener {
        fun onLanguageSelected(language: String, modId: String)
    }

    override fun onLanguageSelected(language: String, modId: String) {
        languageBottomSheetItemListener.onLanguageSelected(language, modId)
    }

}