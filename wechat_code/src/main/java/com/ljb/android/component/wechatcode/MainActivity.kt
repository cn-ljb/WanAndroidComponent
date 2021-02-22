package com.ljb.android.component.wechatcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.ljb.android.component.wechatcode.view.fragment.WeChatCodeMainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_main).setOnClickListener { goWeChatCode() }
    }

    private fun goWeChatCode() {
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(R.id.ll_content, WeChatCodeMainFragment())
        beginTransaction.commit()
    }
}