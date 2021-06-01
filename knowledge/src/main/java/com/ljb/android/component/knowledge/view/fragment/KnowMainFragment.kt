package com.ljb.android.component.knowledge.view.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.SizeUtils
import com.gyf.immersionbar.ImmersionBar
import com.ljb.android.comm.adapter.decoration.RVItemDecorationMargin
import com.ljb.android.comm.mvp.CommMvpFragment
import com.ljb.android.comm.router.RouterConfig
import com.ljb.android.comm.router.RouterManager
import com.ljb.android.component.knowledge.R
import com.ljb.android.component.knowledge.adapter.KnowMainListAdapter
import com.ljb.android.component.knowledge.bean.KnowMainListBean
import com.ljb.android.component.knowledge.contract.KnowMainContract
import com.ljb.android.component.knowledge.databinding.KnowFragmentMainBinding
import com.ljb.android.component.knowledge.presenter.KnowMainPresenter

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/02/21
 * @Description input description
 **/
@Route(path = RouterConfig.Fragment.KNOW_MAIN)
class KnowMainFragment : CommMvpFragment<KnowMainContract.IPresenter, KnowFragmentMainBinding>(),
    KnowMainContract.IView,
    SwipeRefreshLayout.OnRefreshListener {

    private val mListAdapter = KnowMainListAdapter()

    override fun registerPresenter() = KnowMainPresenter::class.java

    override fun getLayoutId() = R.layout.know_fragment_main

    override fun registerBinding(): KnowFragmentMainBinding {
        return KnowFragmentMainBinding.inflate(layoutInflater, mParentView, false)
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
        mBind.swRefresh.isRefreshing = true
        getPresenter().getKnowMainList()
    }

    private fun initTitleView() {
        mBindTitleBar.layoutToolbar.setBackgroundResource(R.drawable.comm_shape_orange_gradient)
        mBindTitleBar.layoutToolbar.setOnClickListener { mBind.rvList.scrollToPosition(0) }
        setTitleText(R.string.know_main, resources.getColor(R.color.color_white))
        setTitleLeftImage(R.mipmap.comm_icon_home_left_menu, View.OnClickListener {
            openOrCloseDrawerLeft()
        })
        setTitleRightImage(R.mipmap.comm_icon_search, View.OnClickListener {
            // go search
            goSearch()
        })
    }

    private fun initRecyclerView() {
        mBind.swRefresh.apply {
            setColorSchemeResources(
                R.color.color_FD7A04,
                R.color.color_FEC896
            )
            setOnRefreshListener(this@KnowMainFragment)
        }

        mBind.rvList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = mListAdapter
            mListAdapter.setOnItemClickListener { _, _, position ->
                // TODO 打开二级分页
                showToast(R.string.comm_wait_develop)
            }
            addItemDecoration(
                RVItemDecorationMargin(
                    bottom = SizeUtils.dp2px(10f),
                    onlyLastItem = true
                )
            )
        }
    }

    private fun openOrCloseDrawerLeft() {
        RouterManager.getAppService()?.openOrCloseDrawerLeft(activity!!)
    }

    override fun onRefresh() {
        getPresenter().getKnowMainList()
    }

    override fun onKnowMainListSuccess(data: KnowMainListBean) {
        mBind.swRefresh.isRefreshing = false
        mListAdapter.data.clear()
        mListAdapter.data.addAll(data.data)
        mListAdapter.notifyDataSetChanged()
    }

    override fun onLoadPageError() {
        mBind.swRefresh.isRefreshing = false
    }

    private fun goSearch() {
        RouterManager.goActivity(RouterConfig.Activity.SEARCH_MAIN)
    }


}
