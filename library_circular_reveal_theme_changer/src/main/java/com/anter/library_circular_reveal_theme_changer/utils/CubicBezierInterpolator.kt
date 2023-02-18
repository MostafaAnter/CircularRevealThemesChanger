package com.anter.library_circular_reveal_theme_changer.utils

import android.graphics.PointF
import android.view.animation.Interpolator

/**
 * Created by Mostafa Anter on 5/1/21.
 */
class CubicBezierInterpolator(start: PointF, end: PointF) : Interpolator {
    protected var start: PointF
    protected var end: PointF
    protected var a = PointF()
    protected var b = PointF()
    protected var c = PointF()

    constructor(startX: Float, startY: Float, endX: Float, endY: Float) : this(
        PointF(
            startX,
            startY
        ), PointF(endX, endY)
    ) {
    }

    constructor(startX: Double, startY: Double, endX: Double, endY: Double) : this(
        startX.toFloat(), startY.toFloat(), endX.toFloat(), endY.toFloat()
    ) {
    }

    override fun getInterpolation(time: Float): Float {
        return getBezierCoordinateY(getXForTime(time))
    }

    protected fun getBezierCoordinateY(time: Float): Float {
        c.y = 3 * start.y
        b.y = 3 * (end.y - start.y) - c.y
        a.y = 1 - c.y - b.y
        return time * (c.y + time * (b.y + time * a.y))
    }

    protected fun getXForTime(time: Float): Float {
        var x = time
        var z: Float
        for (i in 1..13) {
            z = getBezierCoordinateX(x) - time
            if (Math.abs(z) < 1e-3) {
                break
            }
            x -= z / getXDerivate(x)
        }
        return x
    }

    private fun getXDerivate(t: Float): Float {
        return c.x + t * (2 * b.x + 3 * a.x * t)
    }

    private fun getBezierCoordinateX(time: Float): Float {
        c.x = 3 * start.x
        b.x = 3 * (end.x - start.x) - c.x
        a.x = 1 - c.x - b.x
        return time * (c.x + time * (b.x + time * a.x))
    }

    companion object {
        val DEFAULT = CubicBezierInterpolator(0.25, 0.1, 0.25, 1.0)
        val EASE_OUT = CubicBezierInterpolator(0.0, 0.0, .58, 1.0)
        val EASE_OUT_QUINT = CubicBezierInterpolator(.23, 1.0, .32, 1.0)
        val EASE_IN = CubicBezierInterpolator(.42, 0.0, 1.0, 1.0)
        val EASE_BOTH = CubicBezierInterpolator(.42, 0.0, .58, 1.0)
        val EASE_IN_OUT_QUAD = CubicBezierInterpolator(0.455, 0.03, 0.515, 0.955)
    }

    init {
        require(!(start.x < 0 || start.x > 1)) { "startX value must be in the range [0, 1]" }
        require(!(end.x < 0 || end.x > 1)) { "endX value must be in the range [0, 1]" }
        this.start = start
        this.end = end
    }
}