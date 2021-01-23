package com.yuelvdaren.www.user.view.act

import com.ljb.android.comm.mvp.CommMvpActivity
import com.yuelvdaren.www.user.R
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

    override fun registerPresenter() = UserLoginPresenter::class.java

    override fun getLayoutId() = R.layout.activity_userlogin

    override fun registerBinding(): ActivityUserloginBinding {
        return ActivityUserloginBinding.inflate(layoutInflater, mParentView, false)
    }



}
