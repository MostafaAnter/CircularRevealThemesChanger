package com.anter.library_circular_reveal_theme_changer.utils

import android.app.Activity
import android.view.View

// utility method to hide bottom navigation
fun Activity.hideBottomNavigation() {
    val decorView = window.decorView
    val uiOptions = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    } else {
        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }
    decorView.systemUiVisibility = uiOptions
}