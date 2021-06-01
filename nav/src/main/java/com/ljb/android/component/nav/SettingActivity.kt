package com.ljb.android.component.nav

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.ljb.android.component.nav.view.fragment.NavMainFragment

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nav_activity_setting)

        findViewById<Button>(R.id.btn_main).setOnClickListener { goNav() }
    }

    private fun goNav() {
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(R.id.ll_content, NavMainFragment())
        beginTransaction.commit()
    }
}