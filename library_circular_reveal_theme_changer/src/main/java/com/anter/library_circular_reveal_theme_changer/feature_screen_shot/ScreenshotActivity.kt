package com.anter.library_circular_reveal_theme_changer.feature_screen_shot

import android.animation.Animator
import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewAnimationUtils
import android.widget.ImageView
import com.anter.library_circular_reveal_theme_changer.R
import com.anter.library_circular_reveal_theme_changer.utils.CubicBezierInterpolator
import com.anter.library_circular_reveal_theme_changer.utils.StaticData
import com.anter.library_circular_reveal_theme_changer.utils.hideBottomNavigation


class ScreenshotActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideBottomNavigation()
        setContentView(R.layout.activity_screenshot)

        val bitmap = StaticData.screenshotBitmap
        val posX = intent.getIntExtra("posX", -1)
        val posY = intent.getIntExtra("posY", -1)

        val screenshot = findViewById<ImageView>(R.id.sub_settings_screenshot)
        screenshot.setImageBitmap(bitmap)
        screenshot.scaleType = ImageView.ScaleType.MATRIX
        screenshot.visibility = VISIBLE

        val listener = object: View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                Handler(Looper.getMainLooper()).postDelayed({
                    //Do something after 100ms
                    startCircularAnimation(bitmap!!, posX, posY)
                }, 1000)

            }
            override fun onViewDetachedFromWindow(v: View) {}
        }
        screenshot.addOnAttachStateChangeListener(listener)
    }

    @SuppressLint("NewApi")
    private fun startCircularAnimation(bitmap: Bitmap, posX: Int, posY: Int) {
        val screenshot = findViewById<ImageView>(R.id.sub_settings_screenshot)
        screenshot.setImageBitmap(bitmap)
        screenshot.scaleType = ImageView.ScaleType.MATRIX
        screenshot.visibility = VISIBLE

        // Final radius is approximated here.
        val finalRadius = 1500f
        val anim = ViewAnimationUtils.createCircularReveal(screenshot, posX, posY, finalRadius, 0f)
        anim.duration = 1000
        anim.interpolator = CubicBezierInterpolator.EASE_IN_OUT_QUAD
        val animationListener = object: Animator.AnimatorListener {
            override fun onAnimationEnd(animation: Animator) {
                screenshot.setImageDrawable(null)
                screenshot.visibility = View.INVISIBLE
                finish()
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
            override fun onAnimationStart(animation: Animator) {}
        }
        anim.addListener(animationListener)
        anim.start()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        hideBottomNavigation()
    }
}