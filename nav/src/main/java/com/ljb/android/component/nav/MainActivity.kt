package com.ljb.android.component.nav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.ljb.android.component.nav.view.fragment.NavMainFragment
import com.ljb.android.component.nav.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_main).setOnClickListener { goNav() }
    }

    private fun goNav() {
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(R.id.ll_content, NavMainFragment())
        beginTransaction.commit()
    }
}