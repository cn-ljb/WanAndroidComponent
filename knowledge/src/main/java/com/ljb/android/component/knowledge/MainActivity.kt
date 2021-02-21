package com.ljb.android.component.knowledge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.ljb.android.component.knowledge.view.fragment.KnowMainFragment
import com.ljb.android.component.knowledge.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_main).setOnClickListener { goKnow() }
    }

    private fun goKnow() {
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(R.id.ll_content, KnowMainFragment())
        beginTransaction.commit()
    }
}