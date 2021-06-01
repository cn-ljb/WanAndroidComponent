package com.ljb.android.component.wxcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.ljb.android.component.wxcode.view.fragment.WXCodeMainFragment

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wechat_activity_setting)

        findViewById<Button>(R.id.btn_main).setOnClickListener { goWeChatCode() }
    }

    private fun goWeChatCode() {
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(R.id.ll_content, WXCodeMainFragment())
        beginTransaction.commit()
    }
}