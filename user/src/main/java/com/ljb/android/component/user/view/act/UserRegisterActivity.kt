package com.ljb.android.component.user.view.act

import android.os.Bundle
import com.gyf.immersionbar.ImmersionBar
import com.ljb.android.comm.mvp.CommMvpActivity
import com.ljb.android.component.user.R
import com.ljb.android.component.user.contract.UserRegisterContract
import com.ljb.android.component.user.presenter.UserRegisterPresenter
import com.ljb.android.component.user.bean.LoginBean
import com.ljb.android.component.user.databinding.ActivityUserRegisterBinding

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/23
 * @Description input description
 **/
class UserRegisterActivity :
    CommMvpActivity<UserRegisterContract.IPresenter, ActivityUserRegisterBinding>(),
    UserRegisterContract.IView {

    private var userName: String = ""

    companion object {

        const val KEY_USER_NAME = "key_user_name"

    }

    override fun registerPresenter() = UserRegisterPresenter::class.java

    override fun getLayoutId() = R.layout.activity_user_register

    override fun registerBinding(): ActivityUserRegisterBinding {
        return ActivityUserRegisterBinding.inflate(layoutInflater, mParentView, false)
    }

    override fun supportTitle() = true

    override fun initStatusBar() {
        ImmersionBar.with(this)
            .transparentStatusBar()     //透明状态栏，不写默认透明色
            .statusBarDarkFont(false) //状态栏字体是深色，不写默认为亮色
            .navigationBarDarkIcon(false) //导航栏图标是深色，不写默认为亮色
            .init()
    }

    override fun init(savedInstanceState: Bundle?) {
        userName = intent.getStringExtra(KEY_USER_NAME) ?: ""
    }

    override fun initView() {
        initTitleView()
        mBind.btnRegister.setOnClickListener { registerUser() }
        mBind.etRegisterAccount.setText(userName)
    }

    private fun initTitleView() {
        setTitleText(R.string.user_register, resources.getColor(R.color.color_white))
        setTitleLeftImage(R.mipmap.user_back_white)
        mTitleView.setBackgroundColor(resources.getColor(R.color.color_6385F8))
    }

    private fun registerUser() {
        userName = mBind.etRegisterAccount.text.trim().toString()
        val pwd = mBind.etRegisterPassword.text.trim().toString()
        val confirmPwd = mBind.etRegisterConfirmPassword.text.trim().toString()
        getPresenter().registerUser(userName, pwd, confirmPwd)
    }


    override fun onRegisterUserSuccess(it: LoginBean) {
        showToast(R.string.user_register_success)
        val bundle = Bundle()
        bundle.putString(UserLoginActivity.KEY_USER_NAME , userName)
        goActivity(UserLoginActivity::class.java , bundle)
        finish()
    }

}
