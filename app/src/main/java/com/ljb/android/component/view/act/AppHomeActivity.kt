package com.ljb.android.component.view.act

import android.Manifest
import android.os.Bundle
import com.ljb.android.comm.mvp.CommMvpActivity
import com.ljb.android.comm.utils.XLog
import com.ljb.android.component.R
import com.ljb.android.component.contract.AppHomeContract
import com.ljb.android.component.databinding.ActivityApphomeBinding
import com.ljb.android.component.presenter.AppHomePresenter
import com.yanzhenjie.permission.AndPermission

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/23
 * @Description input description
 **/
class AppHomeActivity : CommMvpActivity<AppHomeContract.IPresenter, ActivityApphomeBinding>(),
    AppHomeContract.IView {

    override fun registerPresenter() = AppHomePresenter::class.java

    override fun getLayoutId() = R.layout.activity_apphome

    override fun registerBinding(): ActivityApphomeBinding {
        return ActivityApphomeBinding.inflate(layoutInflater, mParentView, false)
    }

    override fun init(savedInstanceState: Bundle?) {
        requestInitPermissions()
    }

    override fun initData() {

    }

    private fun requestInitPermissions() {
        AndPermission.with(this)
            .runtime()
            .permission(arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE ,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )).onDenied {
                showToast("为了您的应用体验，请给予权限！")
            }.start()
    }

}
