package com.ljb.android.component.nav.view.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.ljb.android.comm.mvp.CommMvpFragment
import com.ljb.android.comm.router.RouterConfig
import com.ljb.android.comm.router.RouterManager
import com.ljb.android.comm.view.act.CommWebViewActivity
import com.ljb.android.component.nav.R
import com.ljb.android.component.nav.adapter.NavTabAdapter
import com.ljb.android.component.nav.adapter.NavTabContentAdapter
import com.ljb.android.component.nav.bean.NavBean
import com.ljb.android.component.nav.contract.NavMainContract
import com.ljb.android.component.nav.databinding.FragmentNavMainBinding
import com.ljb.android.component.nav.presenter.NavMainPresenter

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/02/24
 * @Description input description
 **/
@Route(path = RouterConfig.Fragment.NAV_MAIN)
class NavMainFragment : CommMvpFragment<NavMainContract.IPresenter, FragmentNavMainBinding>(),
    NavMainContract.IView {

    private val mTabAdapter = NavTabAdapter()
    private val mTabContentAdapter = NavTabContentAdapter()

    override fun registerPresenter() = NavMainPresenter::class.java

    override fun getLayoutId() = R.layout.fragment_nav_main

    override fun registerBinding(): FragmentNavMainBinding {
        return FragmentNavMainBinding.inflate(layoutInflater, mParentView, false)
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
        initRecyclerView()
    }


    override fun initData() {
        getPresenter().getNavList()
    }

    private fun initTitleView() {
        mBindTitleBar.layoutToolbar.setBackgroundResource(R.drawable.comm_shape_black_gradient)
        setTitleText(R.string.nav_main, resources.getColor(R.color.color_white))
        setTitleLeftImage(R.mipmap.comm_icon_home_left_menu, View.OnClickListener {
            openOrCloseDrawerLeft()
        })
        setTitleRightImage(R.mipmap.comm_icon_search, View.OnClickListener {
            //TODO  go search
            showToast(R.string.comm_wait_develop)
        })
    }

    private fun initRecyclerView() {
        mBind.rvLeftMenu.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = mTabAdapter
            mTabAdapter.setOnItemClickListener { _, _, position ->
                selectTab(position)
            }
        }

        mBind.rvRightContent.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = mTabContentAdapter
            addOnScrollListener(mOnContentScrollListener)
            mTabContentAdapter.onTagClickListener = { item ->
                val url = item.link
                goWebView(url)
            }
        }
    }

    private val mOnContentScrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == ViewPager.SCROLL_STATE_IDLE) {
                val layoutManager = recyclerView.layoutManager
                if (layoutManager is LinearLayoutManager) {
                    val firstItemPosition: Int = layoutManager.findFirstVisibleItemPosition()
                    mTabAdapter.setSelectedItem(firstItemPosition)
                    scrollToPosition(mBind.rvLeftMenu, firstItemPosition)
                }
            }
        }
    }

    private fun selectTab(position: Int) {
        mTabAdapter.setSelectedItem(position)
        scrollToPosition(mBind.rvLeftMenu, position)
        scrollToPosition(mBind.rvRightContent, position)
    }

    private fun scrollToPosition(rv: RecyclerView, position: Int) {
        val layoutManager = rv.layoutManager
        if (layoutManager is LinearLayoutManager) {
            layoutManager.scrollToPositionWithOffset(position, 0)
        }
    }

    private fun openOrCloseDrawerLeft() {
        RouterManager.getAppService()?.openOrCloseDrawerLeft(activity!!)
    }

    override fun onNavListSuccess(data: NavBean) {
        mTabAdapter.data.clear()
        mTabAdapter.data.addAll(data.data)
        mTabAdapter.notifyDataSetChanged()

        mTabContentAdapter.data.clear()
        mTabContentAdapter.data.addAll(data.data)
        mTabContentAdapter.notifyDataSetChanged()

        selectTab(0)
    }

    private fun goWebView(url: String) {
        CommWebViewActivity.startActivity(activity!!, url)
    }
}
