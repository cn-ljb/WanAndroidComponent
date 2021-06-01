package com.ljb.android.component.user

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ljb.android.component.user.view.act.UserLoginActivity
import com.ljb.android.component.user.view.fragment.UserLeftDrawerFragment

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_activity_setting)
        findViewById<View>(R.id.btn_user).setOnClickListener { openDrawLeft() }
        findViewById<View>(R.id.btn_login).setOnClickListener { goLogin() }
    }

    private fun goLogin() {
        startActivity(Intent(this, UserLoginActivity::class.java))
    }

    private fun openDrawLeft() {
        findViewById<View>(R.id.fl_draw_left).visibility = View.VISIBLE
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(R.id.fl_draw_left, UserLeftDrawerFragment())
        beginTransaction.commit()
    }

}