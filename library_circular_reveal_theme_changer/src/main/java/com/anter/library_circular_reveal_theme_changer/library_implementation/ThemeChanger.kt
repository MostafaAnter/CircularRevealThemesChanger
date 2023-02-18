package com.anter.library_circular_reveal_theme_changer.library_implementation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.view.View
import com.anter.library_circular_reveal_theme_changer.utils.StaticData
import com.anter.library_circular_reveal_theme_changer.utils.ThemeUtilsImpl
import com.jraska.falcon.Falcon

class ThemeChanger(val context: Activity, val view: View) {

    // region change theme with animation
    private fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    fun switchTheme() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            val windowBitmap = Falcon.takeScreenshotBitmap(context)
            val statusBarHeight = getStatusBarHeight()
            val bitmap = Bitmap.createBitmap(
                windowBitmap,
                0,
                statusBarHeight,
                windowBitmap.width,
                windowBitmap.height - statusBarHeight,
                null,
                true
            )
            StaticData.screenshotBitmap = bitmap
            // get location of change theme button to make animation end in this point
            val location = IntArray(2)
            view.getLocationOnScreen(location)
            // start screenshot activity
            fun internalIntent(context: Context, action: String) = Intent(action).setPackage(context.packageName)
            val intent: Intent = internalIntent(context, "com.anter.library_circular_reveal_theme_changer.open")
                .apply {
                    putExtra("posX", location[0])
                    putExtra("posY", location[1])
                    addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                }
            context.startActivity(intent)
            // 0 param means no animation.
            context.overridePendingTransition(0, 0)
        }

        if (ThemeUtilsImpl().isLightTheme(context)) {
            ThemeUtilsImpl().setNightMode(true, 200)
        } else {
            ThemeUtilsImpl().setNightMode(false, 200)
        }


    }
}