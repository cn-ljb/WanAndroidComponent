package com.ljb.android.component.view.act

import android.Manifest
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ljb.android.comm.mvp.CommMvpActivity
import com.ljb.android.comm.router.RouterConfig
import com.ljb.android.comm.router.RouterManager
import com.ljb.android.component.R
import com.ljb.android.component.contract.AppHomeContract
import com.ljb.android.component.databinding.ActivityAppHomeBinding
import com.ljb.android.component.presenter.AppHomePresenter
import com.yanzhenjie.permission.AndPermission

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/23
 * @Description input description
 **/
@Route(path = RouterConfig.Activity.APP_HOME)
class AppHomeActivity : CommMvpActivity<AppHomeContract.IPresenter, ActivityAppHomeBinding>(),
    AppHomeContract.IView {

    override fun registerPresenter() = AppHomePresenter::class.java

    override fun getLayoutId() = R.layout.activity_app_home

    override fun registerBinding(): ActivityAppHomeBinding {
        return ActivityAppHomeBinding.inflate(layoutInflater, mParentView, false)
    }

    override fun init(savedInstanceState: Bundle?) {
        requestInitPermissions()
    }

    override fun initView() {
        initLeftDrawView()
    }

    private fun initLeftDrawView() {
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(
            R.id.fl_left_drawer,
            RouterManager.getFragment(RouterConfig.Fragment.USER_LEFT_DRAWER)
        )
        beginTransaction.commit()
    }

    override fun initData() {

    }

    private fun requestInitPermissions() {
        AndPermission.with(this)
            .runtime()
            .permission(
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            ).onDenied {
                showToast("为了您的应用体验，请给予权限！")
            }.start()
    }

}
