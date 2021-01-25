package com.karan.nishtharedefined.ui.fragment.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.databinding.HomeFragmentBinding
import com.karan.nishtharedefined.model.HomeMenu
import com.karan.nishtharedefined.prefs.SessionPreferences
import com.karan.nishtharedefined.ui.adapter.HomeAdapter
import com.karan.nishtharedefined.ui.dialog.LanguageChooseDialog
import com.karan.nishtharedefined.ui.dialog.ModuleChooseDialog

class HomeFragment : Fragment(),
    HomeAdapter.OnHomeMenuClickListener,
    ModuleChooseDialog.OnModuleOptionSelectedListener,
    LanguageChooseDialog.OnLanguageSelectedListener {

    private lateinit var bindingHomeFragment: HomeFragmentBinding
    private lateinit var moduleChooseDialog: ModuleChooseDialog
    private val homeViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this, HomeViewModel.Factory(activity.application)).get(
            HomeViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingHomeFragment = DataBindingUtil.inflate(
            inflater,
            R.layout.home_fragment,
            container,
            false
        )
        return bindingHomeFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        homeViewModel.prepareHomeMenuData()
        setHasOptionsMenu(true)
        Log.d("Language", SessionPreferences.language)
    }

    private fun initObserver() {
        homeViewModel.homeMenuList.observe(requireActivity(),
            Observer<ArrayList<HomeMenu>> { t -> initHomeRecyclerView(t!!) })
    }

    private fun initHomeRecyclerView(homeMenu: ArrayList<HomeMenu>) {
        bindingHomeFragment.rvMainMenu.layoutManager = GridLayoutManager(
            requireContext(), 2
        )
        bindingHomeFragment.rvMainMenu.adapter = HomeAdapter(
            homeMenu,
            requireContext(), this
        )
    }

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
            1 -> {

            }
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
            2 -> findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToNishthModuleFragment())
        }
    }

    override fun onLanguageSelected(lang: String) {
        when(lang){
            "en" ->{SessionPreferences.language = "en"}
            "hi" ->{SessionPreferences.language = "hi"}
            "ur" ->{SessionPreferences.language = "ur"}
        }
    }
}