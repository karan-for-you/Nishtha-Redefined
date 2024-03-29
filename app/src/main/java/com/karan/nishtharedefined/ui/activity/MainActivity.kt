package com.karan.nishtharedefined.ui.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.snackbar.Snackbar
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.const.AppConstants
import com.karan.nishtharedefined.databinding.ActivityMainBinding
import com.karan.nishtharedefined.ui.fragment.fragmentsheets.homemenu.HomeMenuBottomSheetFragment
import com.karan.nishtharedefined.utils.IOUtils
import com.karan.nishtharedefined.utils.LanguageManager

/**
 *
 * Nishtha Redefined Android Application written by System Analyst
 * of NISHTHA Team, CIET inspired by original Nishtha Application
 * developer - Ashutosh Kailkura, Mobile Application Developer, CIET
 *
 * - Networking: Retrofit
 * - Offline Storage: Room
 * - Threading: Coroutines
 * - LiveData
 * - Model-View-ViewModel Framework
 * - UI Flow: Android Navigation
 * - GSon
 *
 *
 * Dev Info:
 * - Authored by: Karan Jeet Singh, System Analyst, CIET, NCERT
 * - Email: contactkaran013@gmail.com
 * - GitHub: karan-for-you
 */


class MainActivity : AppCompatActivity(),
    HomeMenuBottomSheetFragment.OnHomeSheetLanguageSelectedListener {

    private lateinit var bindingMainActivity: ActivityMainBinding
    /*private var homeMenuBottomSheetFragment = HomeMenuBottomSheetFragment(
        onHomeSheetLanguageSelectedListener = this
    )*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMainActivity = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //val navController = this.findNavController(R.id.nav_host_fragment)
        //supportActionBar?.setBackgroundDrawable(resources.getDrawable(R.drawable.golden_gradient,null))
        //NavigationUI.setupActionBarWithNavController(this, navController)
        askForPermission()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return NavigationUI.navigateUp(navController, bindingMainActivity.drawerLayout)
    }

    // The only method that matters in setting the Locale
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LanguageManager.setLocale(newBase))
    }

    private fun askForPermission() {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            ) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                // System Generated Dialog Box
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    AppConstants.MY_PERMISSIONS_REQUEST_WRITE_STORAGE
                )
            }
        } else {
            // Permission has already been granted
            IOUtils.createDirectory(context = this)
        }
    }

    /*override fun onBackPressed() {
        if (homeMenuBottomSheetFragment.isAdded) {
            if (homeMenuBottomSheetFragment
                    .bindingHomeMenuBottomSheetFragment.rlLanguageHeader.visibility
                == View.VISIBLE
            )
                hideLanguageUI()
            else homeMenuBottomSheetFragment.dismiss()
        } else finish()
    }

    private fun hideLanguageUI() {
        homeMenuBottomSheetFragment.bindingHomeMenuBottomSheetFragment.rvMenu.visibility =
            View.VISIBLE
        homeMenuBottomSheetFragment.bindingHomeMenuBottomSheetFragment.rvLanguage.visibility =
            View.GONE
    }*/

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            AppConstants.MY_PERMISSIONS_REQUEST_WRITE_STORAGE -> {
                if (grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    // Create Directory
                    IOUtils.createDirectory(context = this)
                } else {
                    Snackbar.make(
                        bindingMainActivity.root,
                        "You have not granted the storage permission." +
                                " You will not be able to download the resources",
                        9000
                    ).setAction(
                        "Grant"
                    ) {
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri: Uri = Uri.fromParts(
                            "package",
                            packageName,
                            null
                        )
                        intent.data = uri
                        startActivity(intent)
                    }.show()
                }
            }
        }
    }

    override fun onHomeSheetLanguageSelected(lang: String) {

    }

    fun recreateActivity(){
        finish()
    }


}