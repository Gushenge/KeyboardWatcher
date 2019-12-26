package com.gushenge.keyboard.utils

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 * Created by Gushenge on 2019/12/26.
 */
object DisplayMetricsUtils {
    /**
     * 获取的屏幕真实高度(含虚拟键所占空间)
     * @param context
     * @return
     */
    fun getScreenHeight(context: Context): Int {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val realMetric = DisplayMetrics()
        try {
            val c = Class.forName("android.view.Display")
            val method = c.getMethod("getRealMetrics", DisplayMetrics::class.java)
            method.invoke(display, realMetric)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        
        return realMetric.heightPixels
    }

    /**
     * 获取的当前显示屏幕的高度(不包含虚拟键所占空间)
     * @param context
     * @return
     */
    fun getDisplayScreenHeight(context: Context): Int {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        //获取的像素宽高不包含虚拟键所占空间
        val metric = DisplayMetrics()
        display.getMetrics(metric)
        return metric.heightPixels
    }

    /**
     * 获取屏幕的密度
     * @param context
     * @return
     */
    fun getDisplayScreenDensity(context: Context): Float {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        //获取的像素宽高不包含虚拟键所占空间
        val metric = DisplayMetrics()
        display.getMetrics(metric)
        return metric.density
    }

    /**
     * 获取虚拟功能键高度
     * @param context
     * @return
     */
    fun getVirtualBarHeight(context: Context): Int {
        return getScreenHeight(context) - getDisplayScreenHeight(context)
    }
}