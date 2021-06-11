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
import com.karan.nishtharedefined.ui.activity.MainActivity
import com.karan.nishtharedefined.ui.activity.nishthamodule.nishthalanguage.NishthaOnlineLanguageActivity
import com.karan.nishtharedefined.ui.adapter.HomeAdapter
import com.karan.nishtharedefined.ui.dialog.LanguageChooseDialog
import com.karan.nishtharedefined.ui.dialog.ModuleChooseDialog
import com.karan.nishtharedefined.ui.fragment.fragmentsheets.homemenu.HomeMenuBottomSheetFragment
import com.karan.nishtharedefined.utils.LanguageManager
import com.karan.nishtharedefined.utils.Logger

class HomeFragment : Fragment(),
    LanguageChooseDialog.OnLanguageSelectedListener,
    ModuleChooseDialog.OnModuleOptionSelectedListener,
    HomeMenuBottomSheetFragment.OnHomeSheetLanguageSelectedListener {

    private lateinit var bindingHomeFragment: HomeFragmentBinding
    private val homeMenuFragmentSheet by lazy {
        HomeMenuBottomSheetFragment(
            onHomeSheetLanguageSelectedListener = this,
            application = activity?.application!!
        )
    }
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
        setHasOptionsMenu(true)
        bindingHomeFragment.tvHomeLanguageSelect.text = when(SessionPreferences.language){
            AppConstants.ENG_FLAG -> getString(R.string.english_language)
            AppConstants.HI_FLAG -> getString(R.string.hindi_language)
            else -> ""
        }
        initLanguageDialog()
        Logger.logDebug("Language", SessionPreferences.language)
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
    }

    override fun onModuleOptionSelected(id: Int) {
        when (id) {
            1 -> findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFaceToFaceFragment())
            2 -> startActivity(Intent(requireContext(), NishthaOnlineLanguageActivity::class.java))
        }
    }

    override fun onHomeSheetLanguageSelected(lang: String) {
        TODO("Not yet implemented")
    }

    override fun onLanguageSelected(lang: String) {
        when (lang) {
            AppConstants.ENG_FLAG -> {
                LanguageManager.setNewLocale(
                    requireContext(), AppConstants.ENG_FLAG
                )
                bindingHomeFragment.tvHomeLanguageSelect.text =
                    getString(R.string.english_language)
            }
            AppConstants.HI_FLAG -> {
                LanguageManager.setNewLocale(
                    requireContext(), AppConstants.HI_FLAG
                )
                bindingHomeFragment.tvHomeLanguageSelect.text =
                    getString(R.string.hindi_language)
            }
        }
        activity?.recreate()
    }


}