package com.ljb.android.component.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.ljb.android.component.home.view.fragment.HomeMainFragment

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity_setting)

        findViewById<Button>(R.id.btn_main).setOnClickListener { goHome() }
    }

    private fun goHome() {
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(R.id.ll_content, HomeMainFragment())
        beginTransaction.commit()
    }
}