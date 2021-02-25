package com.ljb.android.component.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.ljb.android.component.project.view.fragment.ProjectMainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn_main).setOnClickListener { goProject() }
    }

    private fun goProject() {
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(R.id.ll_content, ProjectMainFragment())
        beginTransaction.commit()
    }
}