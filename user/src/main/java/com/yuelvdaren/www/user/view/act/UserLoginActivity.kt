package com.yuelvdaren.www.user.view.act

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.UnderlineSpan
import com.ljb.android.comm.mvp.CommMvpActivity
import com.yuelvdaren.www.user.R
import com.yuelvdaren.www.user.bean.LoginBean
import com.yuelvdaren.www.user.contract.UserLoginContract
import com.yuelvdaren.www.user.databinding.ActivityUserloginBinding
import com.yuelvdaren.www.user.presenter.UserLoginPresenter


/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/23
 * @Description input description
 **/
class UserLoginActivity : CommMvpActivity<UserLoginContract.IPresenter, ActivityUserloginBinding>(),
    UserLoginContract.IView {

    private var userName: String = ""

    companion object {

        const val KEY_USER_NAME = "key_user_name"

    }

    override fun registerPresenter() = UserLoginPresenter::class.java

    override fun getLayoutId() = R.layout.activity_userlogin

    override fun registerBinding(): ActivityUserloginBinding {
        return ActivityUserloginBinding.inflate(layoutInflater, mParentView, false)
    }

    override fun supportTitle() = true

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        userName = intent?.getStringExtra(KEY_USER_NAME) ?: ""
    }

    override fun init(savedInstanceState: Bundle?) {
        userName = intent.getStringExtra(KEY_USER_NAME) ?: ""
    }

    override fun initView() {
        setTitleLeftImage(R.mipmap.user_back_black)

        mBind.tvGoRegister.run {
            val spanStr = SpannableString(resources.getString(R.string.user_go_register))
            val colorSpan = ForegroundColorSpan(resources.getColor(R.color.color_39B6DF))
            val sizeSpan = RelativeSizeSpan(1.25f)
            val underSpan = UnderlineSpan()
            spanStr.setSpan(colorSpan, 7, 9, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
            spanStr.setSpan(sizeSpan, 7, 9, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
            spanStr.setSpan(underSpan, 7, 9, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
            text = spanStr
            setOnClickListener { goRegister() }
        }

        mBind.btnLogin.setOnClickListener { login() }
    }

    private fun goRegister() {
        var bundle: Bundle? = null
        val userName = mBind.etLoginAccountEdit.text.trim().toString()
        if (userName.isNotEmpty()) {
            bundle = Bundle()
            bundle.putString(UserRegisterActivity.KEY_USER_NAME, userName)
        }
        goActivity(UserRegisterActivity::class.java, bundle)
    }

    private fun login() {
        userName = mBind.etLoginAccountEdit.text.trim().toString()
        val pwd = mBind.etLoginPasswordEdit.text.trim().toString()
        getPresenter().login(userName, pwd)
    }

    override fun onLoginSuccess(loginBean: LoginBean) {
        //TODO 跳转主页
    }

}
