package com.karan.nishtharedefined.ui.fragment.fragmentsheets.homemenu

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.const.AppConstants
import com.karan.nishtharedefined.databinding.HomeMenuSheetBinding
import com.karan.nishtharedefined.ui.adapter.HomeBottomMenuAdapter
import com.karan.nishtharedefined.ui.dialog.contactdebug.ContactDebugDialog
import com.karan.nishtharedefined.utils.DataGenerator

class HomeMenuBottomSheetFragment(
    var onHomeSheetLanguageSelectedListener: OnHomeSheetLanguageSelectedListener,
    var application: Application
) : BottomSheetDialogFragment(),
HomeBottomMenuAdapter.OnHomeBottomMenuClickListener{

    lateinit var bindingHomeMenuBottomSheetFragment: HomeMenuSheetBinding
    private val homeSheetViewModel by lazy {
        val app = application
        ViewModelProvider(this, HomeMenuSheetViewModel.Factory(app))
            .get(HomeMenuSheetViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingHomeMenuBottomSheetFragment = DataBindingUtil.inflate(
            inflater,
            R.layout.home_menu_sheet,
            container,
            false
        )
        return bindingHomeMenuBottomSheetFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareData()
        initLanguageStrips()
        bindingHomeMenuBottomSheetFragment.ivLanguageBack.setOnClickListener {
            bindingHomeMenuBottomSheetFragment.rvMenu.visibility = View.VISIBLE
            bindingHomeMenuBottomSheetFragment.rvLanguage.visibility = View.GONE
        }
    }

    private fun prepareData(){
        val menuData = DataGenerator.prepareMenuData(requireContext())
        bindingHomeMenuBottomSheetFragment.rvMenu.layoutManager =
            LinearLayoutManager(requireContext())
        bindingHomeMenuBottomSheetFragment.rvMenu.adapter =
            HomeBottomMenuAdapter(
                context = requireContext(),
                listItems = menuData,
                onHomeBottomMenuClickListener = this
            )
    }

    private fun initLanguageStrips(){
        bindingHomeMenuBottomSheetFragment.llEnglish.setOnClickListener {
            onHomeSheetLanguageSelectedListener.onHomeSheetLanguageSelected(AppConstants.ENG_FLAG)
            dismiss()
        }
        bindingHomeMenuBottomSheetFragment.llHindi.setOnClickListener {
            onHomeSheetLanguageSelectedListener.onHomeSheetLanguageSelected(AppConstants.HI_FLAG)
            dismiss()
        }
        bindingHomeMenuBottomSheetFragment.llUrdu.setOnClickListener {
            onHomeSheetLanguageSelectedListener.onHomeSheetLanguageSelected(AppConstants.UR_FLAG)
            dismiss()
        }
    }

    override fun onHomeBottomMenuClicked(position: Int) {
        when(position){
            1->{
                bindingHomeMenuBottomSheetFragment.rvMenu.visibility = View.GONE
                bindingHomeMenuBottomSheetFragment.rvLanguage.visibility = View.VISIBLE
            }
            7->{
                // Catch Exception if application returns null
                ContactDebugDialog(mContext = requireContext()).show()
            }
            8->{
                homeSheetViewModel.makeDeleteLanguages()
                homeSheetViewModel.makeDeleteModules()
                homeSheetViewModel.makeDeleteModuleDetails()
            }
        }
        if(position != 1)
            dismiss()
    }

    interface OnHomeSheetLanguageSelectedListener {
        fun onHomeSheetLanguageSelected(lang: String)
    }

}