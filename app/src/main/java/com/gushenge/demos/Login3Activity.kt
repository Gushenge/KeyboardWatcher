package com.gushenge.demos

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import com.gushenge.keyboard.KeyboardWatcher
import com.gushenge.keyboard.KeyboardWatcher.Companion.get

/**
 * Created by Gushenge on 2019/12/26.
 */
class Login3Activity : Activity() {
    // UI references.
    private lateinit var keyboardWatcher: KeyboardWatcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login3)
        keyboardWatcher = get().init(this, window.decorView) { b, i ->
            if (b) {
                Toast.makeText(application, "键盘显示了！高度差：$i", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(application, "键盘隐藏了！高度差：$i", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        keyboardWatcher.release()
    }
}