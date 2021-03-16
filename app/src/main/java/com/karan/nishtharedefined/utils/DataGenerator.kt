package com.karan.nishtharedefined.utils

import android.content.Context
import com.karan.nishtharedefined.R
import com.karan.nishtharedefined.model.home.HomeMenu
import com.karan.nishtharedefined.model.home.MenuData

class DataGenerator{

    companion object{
        fun prepareHomeMenuData(context : Context) : ArrayList<HomeMenu>{
            val homeMenu = ArrayList<HomeMenu>()
            homeMenu.add(HomeMenu(1,context.getString(R.string.home_screen_library),R.drawable.home_library))
            homeMenu.add(HomeMenu(2,context.getString(R.string.home_screen_coursemodule),R.drawable.home_courmod))
            homeMenu.add(HomeMenu(3,context.getString(R.string.home_screen_webtutoril),R.drawable.home_webtutorial))
            homeMenu.add(HomeMenu(4,context.getString(R.string.home_screen_submitshowcasevideo),R.drawable.home_submitvid))
            homeMenu.add(HomeMenu(5,context.getString(R.string.home_screen_additionaleresourse),R.drawable.home_addres))
            homeMenu.add(HomeMenu(6,context.getString(R.string.home_screen_faq),R.drawable.home_feedback))
            return homeMenu
        }

        fun prepareMenuData(context: Context) : ArrayList<MenuData>{
            val menuData = ArrayList<MenuData>()
            menuData.add(MenuData(1,context.getString(R.string.home_screen_about),R.drawable.ic_baseline_info_24))
            menuData.add(MenuData(2,context.getString(R.string.home_screen_changelanguage),R.drawable.ic_baseline_language_24))
            menuData.add(MenuData(3,"Change Theme",R.drawable.ic_baseline_color_lens_24))
            menuData.add(MenuData(4,context.getString(R.string.home_screen_website),R.drawable.ic_baseline_web_24))
            menuData.add(MenuData(5,context.getString(R.string.home_screen_feedback),R.drawable.ic_baseline_feedback_24))
            menuData.add(MenuData(5,context.getString(R.string.home_screen_feedback),R.drawable.ic_baseline_feedback_24))
            menuData.add(MenuData(6,context.getString(R.string.home_screen_share),R.drawable.ic_baseline_share_24))
            menuData.add(MenuData(7,context.getString(R.string.home_screen_contactus),R.drawable.ic_baseline_connect_without_contact_24))
            menuData.add(MenuData(8,"Contacts Debugging",R.drawable.ic_baseline_bug_report_24))
            menuData.add(MenuData(9,"Delete Database",R.drawable.ic_baseline_delete_24))
            return menuData
        }
    }

}