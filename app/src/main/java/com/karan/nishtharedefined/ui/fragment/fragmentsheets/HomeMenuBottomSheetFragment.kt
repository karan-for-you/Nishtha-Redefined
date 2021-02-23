package com.karan.nishtharedefined.ui.fragment.fragmentsheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.databinding.HomeMenuSheetBinding
import com.karan.nishtharedefined.ui.adapter.HomeBottomMenuAdapter
import com.karan.nishtharedefined.ui.dialog.contactdebug.ContactDebugDialog
import com.karan.nishtharedefined.utils.DataGenerator

class HomeMenuBottomSheetFragment : BottomSheetDialogFragment(),
HomeBottomMenuAdapter.OnHomeBottomMenuClickListener{

    private lateinit var bindingHomeMenuBottomSheetFragment: HomeMenuSheetBinding

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
                requireContext(),
                menuData,
                this
            )
    }

    override fun onHomeBottomMenuClicked(position: Int) {
        when(position){
            1->{
                bindingHomeMenuBottomSheetFragment.rvMenu.visibility = View.GONE
                bindingHomeMenuBottomSheetFragment.rvLanguage.visibility = View.VISIBLE
            }
            7->{
                ContactDebugDialog(
                    context = requireContext(),
                    application = activity?.application!!,
                    this
                ).show()
            }
        }
        if(position != 1)
            dismiss()
    }

}