package com.gov.mybase.utils

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 * ===================================
 * @PackageName ：com.gov.mybase.utils
 * @Description ：TODO
 * @Author ：fengxiaochuan
 * @Date ：2023/7/7 14:08
 * @Version ：1.0
 * ===================================
 */
class DensityUtils {
    companion object {
        @JvmStatic
        fun dp2px(context: Context, scale: Float): Int {
            return (scale * context.resources.displayMetrics.density + 0.5f).toInt()
        }

        @JvmStatic
        fun px2dp(context: Context, scale: Float): Int {
            return (scale / context.resources.displayMetrics.density + 0.5f).toInt()
        }

        @JvmStatic
        fun getDensityDpi(context: Context): Int {
            val displayMetrics = DisplayMetrics()

            (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getMetrics(
                displayMetrics
            )
            return displayMetrics.densityDpi
        }

        @JvmStatic
        fun getScaledDensity(context: Context): Float {
            val displayMetrics = DisplayMetrics()
            (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getMetrics(
                displayMetrics
            )
            return displayMetrics.scaledDensity
        }
    }
}