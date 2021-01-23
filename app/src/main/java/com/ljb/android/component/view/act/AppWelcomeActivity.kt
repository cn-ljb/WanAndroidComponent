package com.ljb.android.component.view.act

import com.gyf.immersionbar.ImmersionBar
import com.ljb.android.comm.mvp.CommMvpActivity
import com.ljb.android.component.R
import com.ljb.android.component.contract.AppWelcomeContract
import com.ljb.android.component.databinding.ActivityAppwelcomeBinding
import com.ljb.android.component.presenter.AppWelcomePresenter
import java.util.concurrent.TimeUnit

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/23
 * @Description input description
 **/
class AppWelcomeActivity : CommMvpActivity<AppWelcomeContract.IPresenter, ActivityAppwelcomeBinding>(),
    AppWelcomeContract.IView {

    override fun registerPresenter() = AppWelcomePresenter::class.java

    override fun getLayoutId() = R.layout.activity_appwelcome

    override fun registerBinding(): ActivityAppwelcomeBinding {
        return ActivityAppwelcomeBinding.inflate(layoutInflater, mParentView, false)
    }

    override fun initStatusBar() {
        ImmersionBar.with(this)
            .transparentStatusBar()     //透明状态栏，不写默认透明色
            .statusBarDarkFont(false) //状态栏字体是深色，不写默认为亮色
            .navigationBarDarkIcon(false) //导航栏图标是深色，不写默认为亮色
            .init()
    }

    override fun initData() {
        getPresenter().delayGoHome(2000, TimeUnit.MILLISECONDS)
    }

    override fun goHome() {
        goActivity(AppHomeActivity::class.java)
        finish()
    }
}
