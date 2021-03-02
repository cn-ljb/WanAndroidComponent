package com.ljb.android.component.home.view.fragment

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.ViewPager
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.SizeUtils
import com.gyf.immersionbar.ImmersionBar
import com.ljb.android.comm.adapter.decoration.RVItemDecorationMargin
import com.ljb.android.comm.common.LocUser
import com.ljb.android.comm.eventbus.UserEvent
import com.ljb.android.comm.mvp.CommMvpFragment
import com.ljb.android.comm.router.RouterConfig
import com.ljb.android.comm.router.RouterManager
import com.ljb.android.comm.view.act.CommWebViewActivity
import com.ljb.android.component.home.R
import com.ljb.android.component.home.adapter.HomeBannerAdapter
import com.ljb.android.component.home.adapter.HomeListAdapter
import com.ljb.android.component.home.bean.BannerBean
import com.ljb.android.component.home.bean.HomeListBean
import com.ljb.android.component.home.contract.HomeMainContract
import com.ljb.android.component.home.databinding.FragmentHomeMainFragmentBinding
import com.ljb.android.component.home.databinding.HomeLayoutBannerBinding
import com.ljb.android.component.home.presenter.HomeMainPresenter
import com.youth.banner.BannerConfig
import org.greenrobot.eventbus.Subscribe

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/27
 * @Description input description
 **/
@Route(path = RouterConfig.Fragment.HOME_MAIN)
class HomeMainFragment :
    CommMvpFragment<HomeMainContract.IPresenter, FragmentHomeMainFragmentBinding>(),
    HomeMainContract.IView, SwipeRefreshLayout.OnRefreshListener {

    private var mBannerBind: HomeLayoutBannerBinding? = null
    private var mBannerBean: BannerBean? = null
    private val mListAdapter = HomeListAdapter()
    private var mPage = 0

    override fun registerPresenter() = HomeMainPresenter::class.java

    override fun getLayoutId() = R.layout.fragment_home_main_fragment

    override fun registerBinding(): FragmentHomeMainFragmentBinding {
        return FragmentHomeMainFragmentBinding.inflate(layoutInflater, mParentView, false)
    }

    override fun initStatusBar() {
        ImmersionBar.with(this)
            .transparentStatusBar()     //透明状态栏，不写默认透明色
            .statusBarDarkFont(false) //状态栏字体是深色，不写默认为亮色
            .navigationBarDarkIcon(false) //导航栏图标是深色，不写默认为亮色
            .init()
    }

    override fun supportTitle() = true

    override fun onStart() {
        super.onStart()
        mBannerBind?.banner?.startAutoPlay()
    }

    override fun onPause() {
        super.onPause()
        mBannerBind?.banner?.stopAutoPlay()
    }

    override fun initView() {
        initTitleView()
        initRecyclerView()
        initBannerView()
    }

    override fun initData() {
        mBind.swRefresh.isRefreshing = true
        getPresenter().getBannerAndHomeListCache()
        getPresenter().getBannerAndHomeList(mPage)
    }

    override fun onRefresh() {
        mPage = 0
        getPresenter().getBannerAndHomeList(mPage)
    }

    private fun onLoadMore() {
        getPresenter().getHomeList(mPage)
    }

    private fun initTitleView() {
        mBindTitleBar.layoutToolbar.setBackgroundResource(R.drawable.comm_shape_blue_gradient)
        mBindTitleBar.layoutToolbar.setOnClickListener { mBind.rvList.scrollToPosition(0) }
        setTitleText(R.string.home_main, resources.getColor(R.color.color_white))
        setTitleLeftImage(R.mipmap.comm_icon_home_left_menu, View.OnClickListener {
            openOrCloseDrawerLeft()
        })
        setTitleRightImage(R.mipmap.comm_icon_search, View.OnClickListener {
            //TODO  go search
            showToast(R.string.comm_wait_develop)
        })
    }

    private fun initRecyclerView() {
        mBind.swRefresh.apply {
            setColorSchemeResources(
                R.color.color_39B6DF,
                R.color.color_4FFDFD,
                R.color.color_6385F8
            )
            setOnRefreshListener(this@HomeMainFragment)
        }

        mBind.rvList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = mListAdapter
            mListAdapter.removeAllHeaderView()
            mListAdapter.loadMoreModule.isEnableLoadMore = true
            mListAdapter.loadMoreModule.isEnableLoadMoreIfNotFullPage = false
            mListAdapter.loadMoreModule.setOnLoadMoreListener { onLoadMore() }
            mListAdapter.mCollectListener = { doCollect(it) }
            mListAdapter.setOnItemClickListener { _, _, position ->
                val url = mListAdapter.data[position].link
                goWebView(url)
            }
            addItemDecoration(
                RVItemDecorationMargin(
                    bottom = SizeUtils.dp2px(10f),
                    onlyLastItem = true
                )
            )
        }
    }

    private fun initBannerView() {
        mBannerBind = HomeLayoutBannerBinding.inflate(layoutInflater, mBind.rvList, false)
        mBannerBind?.banner?.apply {
            setBannerStyle(BannerConfig.NOT_INDICATOR)
            isAutoPlay(true)
            setViewPagerIsScroll(true)
            setOffscreenPageLimit(3)
            setDelayTime(2500)
            setImageLoader(HomeBannerAdapter())
            setOnBannerListener { position ->
                mBannerBean?.run {
                    goWebView(data[position].url)
                }
            }
            setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {}

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                }

                override fun onPageSelected(position: Int) {
                    changeBannerBottomDesc(position)
                }
            })
            mListAdapter.addHeaderView(mBannerBind!!.root)
        }
    }


    override fun onBannerSuccess(bannerBean: BannerBean) {
        mBannerBean = bannerBean
        mBannerBind?.banner?.setImages(bannerBean.data)
        mBannerBind?.banner?.start()
    }

    override fun onHomeListSuccess(homeListBean: HomeListBean) {
        if (mPage == 0) {
            mBind.swRefresh.isRefreshing = false
            mListAdapter.data.clear()
        } else {
            mListAdapter.loadMoreModule.loadMoreComplete()
        }

        if (homeListBean.data.datas.isEmpty()) {
            //没有更多数据
            mListAdapter.loadMoreModule.loadMoreEnd(true)
        } else {
            mListAdapter.data.addAll(homeListBean.data.datas)
            mListAdapter.notifyDataSetChanged()
            mPage++
        }
    }

    override fun onLoadPageError() {
        if (mPage == 0) {
            showToast(R.string.comm_net_error)
            mBind.swRefresh.isRefreshing = false
        } else {
            mListAdapter.loadMoreModule.loadMoreFail()
        }
    }

    override fun onCollectStatus(position: Int, status: Boolean) {
        mListAdapter.data[mListAdapter.getRealPosition(position)].collect = status
        mListAdapter.notifyItemChanged(position)
    }

    private fun doCollect(position: Int) {
        if (!LocUser.isLogIn(activity!!, true)) {
            return
        }
        val item = mListAdapter.data[mListAdapter.getRealPosition(position)]
        if (item.collect) {
            getPresenter().cancelCollect(position, item.id)
        } else {
            getPresenter().doCollect(position, item.id)
        }
    }

    private fun openOrCloseDrawerLeft() {
        RouterManager.getAppService()?.openOrCloseDrawerLeft(activity!!)
    }

    @SuppressLint("SetTextI18n")
    private fun changeBannerBottomDesc(position: Int) {
        mBannerBean?.data?.run {
            val realPos = position % size
            mBannerBind?.tvBannerDesc?.text = get(realPos).title
            mBannerBind?.tvBannerPage?.text = "${position + 1}/${size}"
        }
    }

    private fun goWebView(url: String) {
        CommWebViewActivity.startActivity(activity!!, url)
    }

    override fun supportEventBus() = true

    @Subscribe
    fun onUserEvent(event: UserEvent) {
        when (event.type) {
            UserEvent.EventType.TYPE_LOGOUT,
            UserEvent.EventType.TYPE_LOGIN -> {
                onRefresh()
            }
        }
    }

}
