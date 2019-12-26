package com.gushenge.demos

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import com.gushenge.keyboard.KeyboardWatcher
import com.gushenge.keyboard.KeyboardWatcher.Companion.get
import com.gushenge.keyboard.callback.OnKeyboardStateChangeListener

/**
 * Created by Gushenge on 2019/12/26.
 */
class LoginActivity : Activity() {
    private lateinit var keyboardWatcher: KeyboardWatcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)
        keyboardWatcher = get().init(this, window.decorView, object : OnKeyboardStateChangeListener {
            override fun onKeyboardStateChange(isShow: Boolean, heightDifference: Int) {
                if (isShow) {
                    Toast.makeText(application, "键盘显示了！高度差：$heightDifference", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(application, "键盘隐藏了！高度差：$heightDifference", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        keyboardWatcher.release()
    }
}