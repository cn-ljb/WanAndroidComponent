package com.ljb.android.component.project.view.fragment

import android.graphics.Typeface
import android.text.TextUtils
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.tabs.TabLayout
import com.gyf.immersionbar.ImmersionBar
import com.ljb.android.comm.mvp.CommMvpFragment
import com.ljb.android.comm.router.RouterConfig
import com.ljb.android.comm.router.RouterManager
import com.ljb.android.component.project.contract.ProjectMainContract
import com.ljb.android.component.project.presenter.ProjectMainPresenter
import mvp.ljb.kt.fragment.BaseMvpFragment
import com.ljb.android.component.project.R
import com.ljb.android.component.project.adapter.ProjectTabAdapter
import com.ljb.android.component.project.bean.ProjectTabBean
import com.ljb.android.component.project.databinding.FragmentProjectMainBinding

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/02/26
 * @Description input description
 **/
@Route(path = RouterConfig.Fragment.PROJECT_MAIN)
class ProjectMainFragment :
    CommMvpFragment<ProjectMainContract.IPresenter, FragmentProjectMainBinding>(),
    ProjectMainContract.IView {

    private var mTabViewPagerAdapter: ProjectTabAdapter? = null

    override fun registerPresenter() = ProjectMainPresenter::class.java

    override fun getLayoutId() = R.layout.fragment_project_main

    override fun registerBinding(): FragmentProjectMainBinding {
        return FragmentProjectMainBinding.inflate(layoutInflater, mParentView, false)
    }

    override fun initStatusBar() {
        ImmersionBar.with(this)
            .transparentStatusBar()     //透明状态栏，不写默认透明色
            .statusBarDarkFont(false) //状态栏字体是深色，不写默认为亮色
            .navigationBarDarkIcon(false) //导航栏图标是深色，不写默认为亮色
            .init()
    }

    override fun supportTitle() = true

    override fun initView() {
        initTitleView()
    }

    override fun initData() {
        getPresenter().getTabList()
    }

    private fun initTitleView() {
        mBindTitleBar.layoutToolbar.setBackgroundResource(R.drawable.comm_shape_red_gradient)
        mBindTitleBar.layoutToolbar.setOnClickListener { scrollToTop() }
        setTitleText(R.string.project_main, resources.getColor(R.color.color_white))
        setTitleLeftImage(R.mipmap.comm_icon_home_left_menu, View.OnClickListener {
            openOrCloseDrawerLeft()
        })
        setTitleRightImage(R.mipmap.comm_icon_search, View.OnClickListener {
            //TODO  go search
            showToast(R.string.comm_wait_develop)
        })
    }

    private fun openOrCloseDrawerLeft() {
        RouterManager.getAppService()?.openOrCloseDrawerLeft(activity!!)
    }

    override fun onTabListSuccess(data: ProjectTabBean) {
        mTabViewPagerAdapter = ProjectTabAdapter(data.data, childFragmentManager)
        mBind.viewPage.run {
            offscreenPageLimit = 1
            adapter = mTabViewPagerAdapter
        }

        mBind.tabLayout.run {
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    tab.customView = getTabCustomView(tab)
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {
                    tab.customView = null
                }

                override fun onTabReselected(tab: TabLayout.Tab) {
                    if (tab.customView == null) {
                        tab.customView = getTabCustomView(tab)
                    }
                }
            })
            setupWithViewPager(mBind.viewPage)
            selectTab(getTabAt(0))
        }

    }

    private fun getTabCustomView(tab: TabLayout.Tab): TextView {
        return TextView(activity).apply {
            setLines(1)
            ellipsize = TextUtils.TruncateAt.END
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
            setTextColor(resources.getColor(R.color.color_red))
            typeface = Typeface.DEFAULT_BOLD
            text = tab.text
        }
    }

    private fun scrollToTop() {
        mTabViewPagerAdapter?.getCurFragment()?.scrollToTop()
    }
}
