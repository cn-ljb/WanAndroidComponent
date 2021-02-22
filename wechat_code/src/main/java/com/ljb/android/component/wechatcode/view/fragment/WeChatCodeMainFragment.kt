package com.ljb.android.component.wechatcode.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.ljb.android.comm.mvp.CommMvpFragment
import com.ljb.android.comm.router.RouterConfig
import com.ljb.android.comm.router.RouterManager
import com.ljb.android.component.wechatcode.contract.WeChatCodeMainContract
import com.ljb.android.component.wechatcode.presenter.WeChatCodeMainPresenter
import com.ljb.android.component.wechatcode.R
import com.ljb.android.component.wechatcode.bean.WCodeTabBean
import com.ljb.android.component.wechatcode.databinding.FragmentWeChatCodeMainBinding

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/02/22
 * @Description input description
 **/
@Route(path = RouterConfig.Fragment.WECHAT_CODE_MAIN)
class WeChatCodeMainFragment :
    CommMvpFragment<WeChatCodeMainContract.IPresenter, FragmentWeChatCodeMainBinding>(),
    WeChatCodeMainContract.IView {

    override fun registerPresenter() = WeChatCodeMainPresenter::class.java

    override fun getLayoutId() = R.layout.fragment_we_chat_code_main

    override fun registerBinding(): FragmentWeChatCodeMainBinding {
        return FragmentWeChatCodeMainBinding.inflate(layoutInflater, mParentView, false)
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
        mTitleView.setBackgroundResource(R.drawable.comm_shape_green_gradient)
//        mTitleView.setOnClickListener { mBind.rvList.scrollToPosition(0) }
        setTitleText(R.string.wechat_code_main, resources.getColor(R.color.color_white))
        setTitleLeftImage(R.mipmap.comm_icon_home_left_menu, View.OnClickListener {
            openOrCloseDrawerLeft()
        })
        setTitleRightImage(R.mipmap.comm_icon_search, View.OnClickListener {
            //TODO  go search
        })
    }

    private fun openOrCloseDrawerLeft() {
        RouterManager.getAppService()?.openOrCloseDrawerLeft(activity!!)
    }

    override fun onTabListSuccess(tabBean: WCodeTabBean) {

        mBind.viewPage.adapter = object : FragmentStatePagerAdapter(childFragmentManager) {

            private val fragmentList = ArrayList<Fragment?>()

            override fun getCount(): Int {
                return tabBean.data.size
            }

            override fun getItem(position: Int): Fragment {
                var f: Fragment? = null
                if (position < fragmentList.size) {
                    f = fragmentList.get(position)
                }
                if (f == null) {
                    f = WXArticleListFragment()
                    val bundle = Bundle()
                    bundle.putString(WXArticleListFragment.KEY_ID, tabBean.data[position].id)
                    bundle.putString(WXArticleListFragment.KEY_NAME, tabBean.data[position].name)
                    f.arguments = bundle
                }
                return f
            }


            override fun getPageTitle(position: Int): CharSequence? {
                return tabBean.data[position].name
            }
        }

        mBind.tabLayout.setupWithViewPager(mBind.viewPage)

    }

}
