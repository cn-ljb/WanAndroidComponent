package com.ljb.android.component.home.view.fragment

import com.ljb.android.comm.mvp.CommMvpFragment
import com.ljb.android.component.home.R
import com.ljb.android.component.home.contract.HomeMainContract
import com.ljb.android.component.home.databinding.FragmentHomeMainFragmentBinding
import com.ljb.android.component.home.presenter.HomeMainPresenter

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/27
 * @Description input description
 **/
class HomeMainFragment :
    CommMvpFragment<HomeMainContract.IPresenter, FragmentHomeMainFragmentBinding>(),
    HomeMainContract.IView {

    override fun registerPresenter() = HomeMainPresenter::class.java

    override fun getLayoutId() = R.layout.fragment_home_main_fragment

    override fun registerBinding(): FragmentHomeMainFragmentBinding {
        return FragmentHomeMainFragmentBinding.inflate(layoutInflater, mParentView, false)
    }

    override fun initView() {
        mBind.banner.addBannerLifecycleObserver(this)
    }

    override fun initData() {
        getPresenter().getBannerAndList()
    }

}
