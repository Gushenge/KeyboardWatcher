package com.gushenge.demos

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Gushenge on 2019/12/26.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1.setOnClickListener { startActivity(Intent(this@MainActivity, LoginActivity::class.java)) }
        button2.setOnClickListener { startActivity(Intent(this@MainActivity, Login2Activity::class.java)) }
        button3.setOnClickListener { startActivity(Intent(this@MainActivity, Login3Activity::class.java)) }
    }
}