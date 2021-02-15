package com.karan.nishtharedefined.ui.fragment.home

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.const.AppConstants
import com.karan.nishtharedefined.databinding.HomeFragmentBinding
import com.karan.nishtharedefined.prefs.SessionPreferences
import com.karan.nishtharedefined.ui.adapter.HomeAdapter
import com.karan.nishtharedefined.ui.dialog.LanguageChooseDialog
import com.karan.nishtharedefined.ui.dialog.ModuleChooseDialog
import com.karan.nishtharedefined.ui.fragment.fragmentsheets.HomeMenuBottomSheetFragment
import com.karan.nishtharedefined.ui.fragment.nishthamodule.NishthaOnlineLanguageActivity
import com.karan.nishtharedefined.utils.DataGenerator
import com.karan.nishtharedefined.utils.LanguageManager
import com.karan.nishtharedefined.utils.Logger

class HomeFragment : Fragment(),
    HomeAdapter.OnHomeMenuClickListener,
    ModuleChooseDialog.OnModuleOptionSelectedListener,
    LanguageChooseDialog.OnLanguageSelectedListener {

    private lateinit var bindingHomeFragment: HomeFragmentBinding
    private lateinit var moduleChooseDialog: ModuleChooseDialog
    private var homeMenuFragmentSheet = HomeMenuBottomSheetFragment()
    private var homeFragmentTag = HomeFragment::class.java.simpleName
    /*private val homeViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this, HomeViewModel.Factory(activity.application)).get(
            HomeViewModel::class.java
        )
    }*/

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
            homeMenuFragmentSheet.show(childFragmentManager,"menuBottomSheet")
        }
        //homeViewModel.prepareHomeMenuData()
        //initObserver()
        //(activity as MainActivity).supportActionBar?.title = getString(R.string.app_name)
        //(activity as MainActivity).supportActionBar?.subtitle = ""
        setHasOptionsMenu(true)
        Logger.logDebug("Language", SessionPreferences.language)
    }

    /*private fun initObserver() {
        homeViewModel.homeMenuList.observe(requireActivity(),
            Observer<ArrayList<HomeMenu>> { t -> initHomeRecyclerView(t!!) })
        Logger.logDebug(homeFragmentTag, "Observer Created")
    }

    private fun initHomeRecyclerView(homeMenu: ArrayList<HomeMenu>) {
        bindingHomeFragment.rvMainMenu.layoutManager = GridLayoutManager(
            requireContext(), 2
        )
        bindingHomeFragment.rvMainMenu.adapter = HomeAdapter(
            homeMenu,
            requireContext(), this
        )
        Logger.logDebug("Home", "Recycler View Recreated")
    }*/

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.main_aboutus -> {

            }
            R.id.main_language -> {
                val languageChooseDialog = LanguageChooseDialog(
                    requireContext(),
                    this
                )
                languageChooseDialog.show()
            }
            R.id.main_website -> {

            }
            R.id.main_feedback -> {

            }
            R.id.main_share -> {

            }
            R.id.main_contactus -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onHomeMenuClicked(id: Int) {
        when (id) {
            1 -> findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToLibraryFragment())
            2 -> {
                moduleChooseDialog = ModuleChooseDialog(
                    requireContext(),
                    this
                )
                moduleChooseDialog.show()
            }
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
            2 -> startActivity(Intent(requireContext(),NishthaOnlineLanguageActivity::class.java))
        }
    }

    override fun onLanguageSelected(lang: String) {
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

    private fun setLanguage(lang: String) {
        LanguageManager.setNewLocale(requireContext(), lang)
        activity?.recreate()
    }
}