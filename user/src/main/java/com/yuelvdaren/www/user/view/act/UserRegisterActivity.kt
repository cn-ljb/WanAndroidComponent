package com.yuelvdaren.www.user.view.act

import com.ljb.android.comm.mvp.CommMvpActivity
import com.yuelvdaren.www.user.contract.UserRegisterContract
import com.yuelvdaren.www.user.presenter.UserRegisterPresenter
import com.yuelvdaren.www.user.R
import com.yuelvdaren.www.user.bean.LoginBean
import com.yuelvdaren.www.user.databinding.ActivityUserregisterBinding

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/23
 * @Description input description
 **/
class UserRegisterActivity : CommMvpActivity<UserRegisterContract.IPresenter ,ActivityUserregisterBinding>() , UserRegisterContract.IView {

    override fun registerPresenter() = UserRegisterPresenter::class.java

    override fun getLayoutId() = R.layout.activity_userregister

    override fun registerBinding(): ActivityUserregisterBinding {
        return ActivityUserregisterBinding.inflate(layoutInflater , mParentView , false)
    }

    override fun supportTitle() = true

    override fun initView() {
        setTitleText(R.string.user_register , resources.getColor(R.color.color_white))
        setTitleBackgroundColor(resources.getColor(R.color.color_39B6DF))
        setTitleLeftImage(R.mipmap.user_back_white)
        mBind.registerBtn.setOnClickListener { registerUser()}
    }

    private fun registerUser() {
        val userName = mBind.registerAccountEdit.text.trim().toString()
        val pwd = mBind.registerPasswordEdit.text.trim().toString()
        val confirmPwd = mBind.registerConfirmPasswordEdit.text.trim().toString()
        getPresenter().registerUser(userName , pwd , confirmPwd)
    }

    override fun showLoading() {
       setLoading(true)
    }

    override fun hideLoading() {
        setLoading(false)
    }

    override fun onRegisterUserSuccess(it: LoginBean) {
        showToast(R.string.user_register_success)
        goActivity(UserLoginActivity::class.java)
        finish()
    }

}
