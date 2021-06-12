package com.karan.nishtharedefined.ui.fragment.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.const.AppConstants
import com.karan.nishtharedefined.databinding.HomeFragmentBinding
import com.karan.nishtharedefined.prefs.SessionPreferences
import com.karan.nishtharedefined.ui.activity.nishthamodule.nishthalanguage.NishthaOnlineLanguageActivity
import com.karan.nishtharedefined.ui.dialog.LanguageChooseDialog
import com.karan.nishtharedefined.ui.dialog.ModuleChooseDialog
import com.karan.nishtharedefined.ui.fragment.fragmentsheets.homemenu.HomeMenuBottomSheetFragment
import com.karan.nishtharedefined.utils.LanguageManager
import com.karan.nishtharedefined.utils.Logger

class HomeFragment : Fragment(),
    LanguageChooseDialog.OnLanguageSelectedListener,
    ModuleChooseDialog.OnModuleOptionSelectedListener {

    private lateinit var bindingHomeFragment: HomeFragmentBinding
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        readLanguage()
        initLanguageDialog()
        Logger.logDebug("Language", SessionPreferences.language)
        setupClickListeners()
    }

    private fun readLanguage() {
        bindingHomeFragment.tvHomeLanguageSelect.text = when (SessionPreferences.language) {
            AppConstants.ENG_FLAG -> getString(R.string.english_language)
            AppConstants.HI_FLAG -> getString(R.string.hindi_language)
            else -> ""
        }
    }

    private fun setupClickListeners() {
        bindingHomeFragment.cvMyLibrary.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLibraryFragment())
        }
        bindingHomeFragment.cvModules.setOnClickListener {
            ModuleChooseDialog(
                context = requireContext(),
                onModuleOptionSelectedListener = this
            ).show()
        }
    }

    private fun initLanguageDialog() {
        bindingHomeFragment.tvHomeLanguageSelect.setOnClickListener {
            LanguageChooseDialog(
                requireContext(),
                onLanguageSelectedListener = this
            ).show()
        }
    }

    override fun onResume() {
        super.onResume()
        Logger.logDebug(homeFragmentTag, "Re-init again")
        // TODO: Add code to read the count of the files available in the directory folder
    }

    override fun onModuleOptionSelected(id: Int) {
        when (id) {
            1 -> findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFaceToFaceFragment())
            2 -> startActivity(Intent(requireContext(), NishthaOnlineLanguageActivity::class.java))
        }
    }

    override fun onLanguageSelected(lang: String) {
        when (lang) {
            AppConstants.ENG_FLAG -> {
                setLanguage(lang)
                bindingHomeFragment.tvHomeLanguageSelect.text =
                    getString(R.string.english_language)
            }
            AppConstants.HI_FLAG -> {
                setLanguage(lang)
                bindingHomeFragment.tvHomeLanguageSelect.text =
                    getString(R.string.hindi_language)
            }
        }
        activity?.recreate()
    }

    private fun setLanguage(lang: String) {
        LanguageManager.setNewLocale(requireContext(), lang)
    }


}