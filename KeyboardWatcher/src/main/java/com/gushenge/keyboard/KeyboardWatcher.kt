package com.gushenge.keyboard

import android.content.Context
import android.graphics.Rect
import android.util.Pair
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import com.gushenge.keyboard.callback.OnKeyboardStateChangeListener
import com.gushenge.keyboard.utils.DisplayMetricsUtils

/**
 * Created by Gushenge on 2019/12/26.
 */
class KeyboardWatcher  {
    private var globalLayoutListener: GlobalLayoutListener? = null
    private lateinit var context: Context
    private var decorView: View? = null
    /**
     * 监听键盘的状态变化
     * @param context
     * @param decorView
     * @param listener
     * @return
     */
    fun init(context: Context, decorView: View, listener: OnKeyboardStateChangeListener?): KeyboardWatcher {
        this.context = context
        this.decorView = decorView
        globalLayoutListener = GlobalLayoutListener(listener)
        addSoftKeyboardChangedListener()
        return this
    }

    /**
     * 释放资源
     */
    fun release() {
        removeSoftKeyboardChangedListener()
        globalLayoutListener = null
    }

    /**
     * 取消软键盘状态变化监听
     */
    private fun removeSoftKeyboardChangedListener() {
        if (globalLayoutListener != null && null != decorView) {
            decorView!!.viewTreeObserver.removeOnGlobalLayoutListener(globalLayoutListener)
        }
    }

    /**
     * 注册软键盘状态变化监听
     */
    private fun addSoftKeyboardChangedListener() {
        if (globalLayoutListener != null && null != decorView) {
            removeSoftKeyboardChangedListener()
            decorView!!.viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
        }
    }

    /**
     * 判断键盘是否显示
     * @param context
     * @param decorView
     * @return
     */
    fun isKeyboardShowing(context: Context, decorView: View): Pair<Boolean, Int> {
        val outRect = Rect()
        //指当前Window实际的可视区域大小，通过decorView获取到程序显示的区域，包括标题栏，但不包括状态栏。
        decorView.getWindowVisibleDisplayFrame(outRect)
        val displayScreenHeight = DisplayMetricsUtils.getDisplayScreenHeight(context)
        //如果屏幕高度和Window可见区域高度差值大于0，则表示软键盘显示中，否则软键盘为隐藏状态。
        val heightDifference = displayScreenHeight - outRect.bottom
        return Pair(heightDifference > 0, heightDifference)
    }

    inner class GlobalLayoutListener(onKeyboardStateChangeListener: OnKeyboardStateChangeListener?) : OnGlobalLayoutListener {
        private var isKeyboardShow = false
        private val onKeyboardStateChangeListener: OnKeyboardStateChangeListener?
        override fun onGlobalLayout() {
            if (null != onKeyboardStateChangeListener && null != decorView) {
                val pair = isKeyboardShowing(context, decorView!!)
                if (pair.first) {
                    onKeyboardStateChangeListener.onKeyboardStateChange(true.also { isKeyboardShow = it }, pair.second!!)
                } else if (isKeyboardShow) {
                    onKeyboardStateChangeListener.onKeyboardStateChange(false.also { isKeyboardShow = it }, pair.second!!)
                }
            }
        }

        init {
            isKeyboardShow = false
            this.onKeyboardStateChangeListener = onKeyboardStateChangeListener
        }
    }

    companion object {
        @JvmStatic
        fun get(): KeyboardWatcher {
            return KeyboardWatcher()
        }
    }
}