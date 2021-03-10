package com.karan.nishtharedefined.ui.fragment.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.const.AppConstants
import com.karan.nishtharedefined.databinding.HomeFragmentBinding
import com.karan.nishtharedefined.prefs.SessionPreferences
import com.karan.nishtharedefined.ui.activity.nishthamodule.nishthalanguage.NishthaOnlineLanguageActivity
import com.karan.nishtharedefined.ui.adapter.HomeAdapter
import com.karan.nishtharedefined.ui.dialog.ModuleChooseDialog
import com.karan.nishtharedefined.ui.fragment.fragmentsheets.HomeMenuBottomSheetFragment
import com.karan.nishtharedefined.utils.DataGenerator
import com.karan.nishtharedefined.utils.LanguageManager
import com.karan.nishtharedefined.utils.Logger

class HomeFragment : Fragment(),
    HomeAdapter.OnHomeMenuClickListener,
    ModuleChooseDialog.OnModuleOptionSelectedListener,
    HomeMenuBottomSheetFragment.OnHomeSheetLanguageSelectedListener {

    private lateinit var bindingHomeFragment: HomeFragmentBinding
    private var homeMenuFragmentSheet = HomeMenuBottomSheetFragment(
        onHomeSheetLanguageSelectedListener = this
    )
    private var homeFragmentTag = HomeFragment::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Logger.logDebug(homeFragmentTag, "onCreateView()")
        bindingHomeFragment = DataBindingUtil.inflate(
            inflater,
            R.layout.home_fragment,
            container,
            false
        )
        return bindingHomeFragment.root
    }

    /**
     * The setting up of Data in GridView according to Locale via Observer
     * is not working correctly. The NORMAL APPROACH without Observer
     * is to be integrated in order to recreate and set the data accordingly.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingHomeFragment.rvMainMenu.layoutManager = GridLayoutManager(
            requireContext(), 2
        )
        bindingHomeFragment.rvMainMenu.adapter = HomeAdapter(
            DataGenerator.prepareHomeMenuData(requireContext()),
            requireContext(), this
        )
        bindingHomeFragment.ivOptionsMenu.setOnClickListener {
            homeMenuFragmentSheet.show(
                childFragmentManager,
                AppConstants.MENU_SHEET_FRAGMENT_CONSTANT_TAG
            )
        }
        setHasOptionsMenu(true)
        Logger.logDebug("Language", SessionPreferences.language)
    }

    override fun onResume() {
        super.onResume()
        Logger.logDebug(homeFragmentTag, "Re-init again")
    }

    override fun onHomeMenuClicked(id: Int) {
        when (id) {
            1 -> findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToLibraryFragment())
            2 -> ModuleChooseDialog(
                    requireContext(),
                    this
                ).show()
            3 -> {
            }
            4 -> {
            }
            5 -> {
            }
            6 -> {
            }
        }
    }

    override fun onModuleOptionSelected(id: Int) {
        when (id) {
            1 -> findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFaceToFaceFragment())
            2 -> startActivity(Intent(requireContext(), NishthaOnlineLanguageActivity::class.java))
        }
    }

    private fun setLanguage(lang: String) {
        LanguageManager.setNewLocale(
            context = requireContext(),
            language = lang
        )
        activity?.recreate()
    }

    override fun onHomeSheetLanguageSelected(lang: String) {
        when (lang) {
            AppConstants.ENG_FLAG ->
                if (SessionPreferences.language != AppConstants.ENG_FLAG)
                    setLanguage(AppConstants.ENG_FLAG)

            AppConstants.HI_FLAG ->
                if (SessionPreferences.language != AppConstants.HI_FLAG)
                    setLanguage(AppConstants.HI_FLAG)

            AppConstants.UR_FLAG ->
                if (SessionPreferences.language != AppConstants.UR_FLAG)
                    setLanguage(AppConstants.UR_FLAG)
        }
    }
}