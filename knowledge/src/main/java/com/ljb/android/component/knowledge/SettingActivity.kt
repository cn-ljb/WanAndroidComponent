package com.ljb.android.component.knowledge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.ljb.android.component.knowledge.view.fragment.KnowMainFragment

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.know_activity_setting)

        findViewById<Button>(R.id.btn_main).setOnClickListener { goKnow() }
    }

    private fun goKnow() {
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(R.id.ll_content, KnowMainFragment())
        beginTransaction.commit()
    }
}