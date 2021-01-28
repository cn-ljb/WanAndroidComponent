package com.ljb.android.component.home.view.fragment

import android.annotation.SuppressLint
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.ljb.android.comm.mvp.CommMvpFragment
import com.ljb.android.comm.router.RouterConfig
import com.ljb.android.comm.router.RouterManager
import com.ljb.android.comm.utils.XLog
import com.ljb.android.component.home.R
import com.ljb.android.component.home.adapter.HomeBannerAdapter
import com.ljb.android.component.home.bean.BannerBean
import com.ljb.android.component.home.contract.HomeMainContract
import com.ljb.android.component.home.databinding.FragmentHomeMainFragmentBinding
import com.ljb.android.component.home.presenter.HomeMainPresenter
import com.youth.banner.listener.OnBannerListener

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/27
 * @Description input description
 **/
@Route(path = RouterConfig.Fragment.HOME_MAIN)
class HomeMainFragment :
    CommMvpFragment<HomeMainContract.IPresenter, FragmentHomeMainFragmentBinding>(),
    HomeMainContract.IView {

    private val mBannerAdapter = HomeBannerAdapter(mutableListOf())

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

    override fun initView() {
        initTitleView()
        mBind.banner.apply {
            addBannerLifecycleObserver(this@HomeMainFragment)
            adapter = mBannerAdapter
            setOnBannerListener(OnBannerListener<BannerBean.DataBean> { data, position ->
                XLog.i("postion:" + position)
                changeBannerBottomView(data, position)
            })
        }
    }

    private fun changeBannerBottomView(data: BannerBean.DataBean, position: Int) {
        mBind.tvBannerDesc.text = data.desc
        mBind.tvBannerPage.text = "${position + 1}/${mBannerAdapter.itemCount}"
    }

    override fun initData() {
        getPresenter().getBannerAndList()
    }

    private fun initTitleView() {
        mTitleView.setBackgroundResource(R.drawable.comm_shape_blue_gradient)
        setTitleText(R.string.home_main, resources.getColor(R.color.color_white))
        setTitleLeftImage(R.mipmap.home_icon_home_left_menu, View.OnClickListener {
            openOrCloseDrawerLeft()
        })
        setTitleRightImage(R.mipmap.home_icon_search, View.OnClickListener {
            //TODO  go search
        })
    }

    private fun openOrCloseDrawerLeft() {
        RouterManager.getAppService()?.openOrCloseDrawerLeft(activity!!)
    }


    override fun onBannerSuccess(bannerBean: BannerBean) {
        mBannerAdapter.setDatas(bannerBean.data)
        mBannerAdapter.notifyDataSetChanged()
    }

}
