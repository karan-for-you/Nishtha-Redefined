package com.karan.nishtharedefined.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.karan.nishtharedefined.R


class IntentUtils {

    companion object {
        fun openPDF(context: Context, linkToOpen: String?) {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(linkToOpen)
                )
            )
        }

        fun openYouTubeLink(context: Context, linkToPlay: String?) {
            val appIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("vnd.youtube:${linkToPlay}")
            )
            appIntent.putExtra("force_fullscreen", true)
            val webIntent =
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/watch?v=$linkToPlay")
                )

            try {
                val title = context.resources.getString(R.string.chooser_title)
                val chooser = Intent.createChooser(appIntent, title)
                context.startActivity(chooser)
            } catch (ex: ActivityNotFoundException) {
                Logger.logError("Error Playing Link", ex.localizedMessage!!.toString())
                context.startActivity(webIntent)
            }
        }

        fun openPresentation(context: Context, linkToDisplay: String?){
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(linkToDisplay)
            try {
                val title = context.resources?.getString(R.string.chooser_title)
                val chooser = Intent.createChooser(i, title)
                context.startActivity(chooser)
            } catch (e: Exception) {
                Logger.logError("Error opening Presentation", e.localizedMessage!!.toString())
                Toast.makeText(context, "Can Not Open link", Toast.LENGTH_SHORT).show()
            }
        }
    }
}