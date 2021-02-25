package com.ljb.android.component.view.act

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.SizeUtils
import com.ljb.android.comm.mvp.CommMvpActivity
import com.ljb.android.comm.router.RouterConfig
import com.ljb.android.comm.router.RouterManager
import com.ljb.android.comm.utils.XLog
import com.ljb.android.component.R
import com.ljb.android.component.contract.AppHomeContract
import com.ljb.android.component.databinding.ActivityAppHomeBinding
import com.ljb.android.component.presenter.AppHomePresenter
import com.next.easynavigation.view.EasyNavigationBar.OnTabClickListener
import com.yanzhenjie.permission.AndPermission


/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/23
 * @Description input description
 **/
@Route(path = RouterConfig.Activity.APP_MAIN)
class AppHomeActivity : CommMvpActivity<AppHomeContract.IPresenter, ActivityAppHomeBinding>(),
    AppHomeContract.IView {

    private val mTabText by lazy {
        arrayOf(
            resources.getString(R.string.app_home),
            resources.getString(R.string.app_setup),
            resources.getString(R.string.app_public_wchat),
            resources.getString(R.string.app_navigation),
            resources.getString(R.string.app_project)
        )
    }

    private val mIconNormal = intArrayOf(
        R.mipmap.app_icon_home_normal,
        R.mipmap.app_icon_setup_normal,
        R.mipmap.app_icon_wechat_normal,
        R.mipmap.app_icon_nav_normal,
        R.mipmap.app_icon_project_normal
    )

    private val mIconSelected = intArrayOf(
        R.mipmap.app_icon_home_selected,
        R.mipmap.app_icon_setup_selected,
        R.mipmap.app_icon_wechat_selected,
        R.mipmap.app_icon_nav_selected,
        R.mipmap.app_icon_project_selected
    )

    private val mFragments = listOf(
        RouterManager.getFragment(RouterConfig.Fragment.HOME_MAIN),
        RouterManager.getFragment(RouterConfig.Fragment.KNOW_MAIN),
        RouterManager.getFragment(RouterConfig.Fragment.WECHAT_CODE_MAIN),
        RouterManager.getFragment(RouterConfig.Fragment.NAV_MAIN),
        RouterManager.getFragment(RouterConfig.Fragment.HOME_MAIN)
    )


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
        initCenterView()
    }

    override fun initData() {

    }

    private fun initCenterView() {
        mBind.navContent.defaultSetting() //恢复默认配置、可用于重绘导航栏
            .titleItems(mTabText) //  Tab文字集合  只传文字则只显示文字
            .normalIconItems(mIconNormal) //  Tab未选中图标集合
            .selectIconItems(mIconSelected) //  Tab选中图标集合
            .fragmentList(mFragments) //  fragment集合
            .fragmentManager(supportFragmentManager)
            .iconSize(30f) //Tab图标大小
            .tabTextSize(10) //Tab文字大小
            .tabTextTop(2) //Tab文字距Tab图标的距离
            .normalTextColor(resources.getColor(R.color.color_BFBFBF)) //Tab未选中时字体颜色
            .selectTextColor(resources.getColor(R.color.color_39B6DF))//Tab选中时字体颜色
            .scaleType(ImageView.ScaleType.CENTER_INSIDE) //同 ImageView的ScaleType
            .navigationBackground(resources.getColor(R.color.color_white))
            .navigationHeight(50)
            .setOnTabClickListener(object : OnTabClickListener {
                override fun onTabSelectEvent(view: View, position: Int): Boolean {
                    //Tab点击事件  return true 页面不会切换
                    changeTabTextUI(position)
                    return false
                }

                override fun onTabReSelectEvent(view: View?, position: Int): Boolean {
                    //Tab重复点击事件
                    return false
                }
            }).canScroll(false)
            .lineHeight(SizeUtils.dp2px(0.5f))
            .build()
    }

    private fun changeTabTextUI(position: Int) {
        var colorId = resources.getColor(R.color.color_39B6DF)
        when (position) {
            0 -> {
                colorId = resources.getColor(R.color.color_39B6DF)
            }
            1 -> {
                colorId = resources.getColor(R.color.color_FD7A04)
            }
            2 -> {
                colorId = resources.getColor(R.color.color_22DD6D)
            }
            3 -> {
                colorId = resources.getColor(R.color.color_black)
            }
        }
        mBind.navContent.selectTextColor(colorId)
    }

    private fun initLeftDrawView() {
        val beginTransaction = supportFragmentManager.beginTransaction()
        beginTransaction.replace(
            R.id.fl_left_drawer,
            RouterManager.getFragment(RouterConfig.Fragment.USER_LEFT_DRAWER)
        )
        beginTransaction.commit()
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


    @SuppressLint("RtlHardcoded")
    fun openOrCloseDrawerLeft() {
        if (mBind.dlDrawer.isDrawerOpen(Gravity.LEFT)) {
            mBind.dlDrawer.closeDrawer(Gravity.LEFT, true)
        } else {
            mBind.dlDrawer.openDrawer(Gravity.LEFT, true)
        }
    }

}
