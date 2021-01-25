package com.ljb.android.component.user.view.act

import android.os.Bundle
import com.ljb.android.comm.mvp.CommMvpActivity
import com.ljb.android.component.user.R
import com.ljb.android.component.user.contract.UserRegisterContract
import com.ljb.android.component.user.presenter.UserRegisterPresenter
import com.ljb.android.component.user.bean.LoginBean
import com.ljb.android.component.user.databinding.ActivityUserregisterBinding

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/23
 * @Description input description
 **/
class UserRegisterActivity :
    CommMvpActivity<UserRegisterContract.IPresenter, ActivityUserregisterBinding>(),
    UserRegisterContract.IView {

    private var userName: String = ""

    companion object {

        const val KEY_USER_NAME = "key_user_name"

    }

    override fun registerPresenter() = UserRegisterPresenter::class.java

    override fun getLayoutId() = R.layout.activity_userregister

    override fun registerBinding(): ActivityUserregisterBinding {
        return ActivityUserregisterBinding.inflate(layoutInflater, mParentView, false)
    }

    override fun supportTitle() = true

    override fun init(savedInstanceState: Bundle?) {
        userName = intent.getStringExtra(KEY_USER_NAME) ?: ""
    }

    override fun initView() {
        setTitleText(R.string.user_register, resources.getColor(R.color.color_white))
        setTitleBackgroundColor(resources.getColor(R.color.color_39B6DF))
        setTitleLeftImage(R.mipmap.user_back_white)
        mBind.btnRegister.setOnClickListener { registerUser() }

        mBind.etRegisterAccount.setText(userName)
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
