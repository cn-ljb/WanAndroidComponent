package com.ljb.android.component.user.view.fragment

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.ljb.android.comm.common.LocUser
import com.ljb.android.comm.event.UserEvent
import com.ljb.android.comm.img.ImageLoader
import com.ljb.android.comm.img.format.ImgFormatEvent
import com.ljb.android.comm.mvp.CommMvpFragment
import com.ljb.android.comm.router.RouterConfig
import com.ljb.android.comm.utils.XLog
import com.ljb.android.component.user.R
import com.ljb.android.component.user.contract.UserLeftDrawerContract
import com.ljb.android.component.user.databinding.FragmentUserleftdrawerBinding
import com.ljb.android.component.user.presenter.UserLeftDrawerPresenter
import com.ljb.android.component.user.view.act.UserLoginActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/25
 * @Description input description
 **/
@Route(path = RouterConfig.Fragment.USER_LEFT_DRAWER)
class UserLeftDrawerFragment :
    CommMvpFragment<UserLeftDrawerContract.IPresenter, FragmentUserleftdrawerBinding>(),
    UserLeftDrawerContract.IView {

    override fun registerPresenter() = UserLeftDrawerPresenter::class.java

    override fun getLayoutId() = R.layout.fragment_userleftdrawer

    override fun registerBinding(): FragmentUserleftdrawerBinding {
        return FragmentUserleftdrawerBinding.inflate(layoutInflater, mParentView, false)
    }

    override fun initStatusBar() {
        ImmersionBar.with(this)
            .transparentStatusBar()     //透明状态栏，不写默认透明色
            .statusBarDarkFont(false) //状态栏字体是深色，不写默认为亮色
            .navigationBarDarkIcon(false) //导航栏图标是深色，不写默认为亮色
            .init()
    }

    override fun initView() {
        mBind.btnLogout.setOnClickListener { getPresenter().logout() }
        changeUserStatus()
    }


    private fun changeUserStatus() {
        val user = LocUser.getUser()
        if (user != null) {
            mBind.btnLogout.visibility = View.VISIBLE
            mBind.tvName.text = user.nickname
            ImageLoader.Builder(user.icon, mBind.ivHeader)
                .format(ImgFormatEvent.Circle)
                .build(this)
                .load()
            mBind.tvName.setOnClickListener(null)
            mBind.ivHeader.setOnClickListener(null)
        } else {
            mBind.btnLogout.visibility = View.GONE
            mBind.tvName.setText(R.string.user_login)
            mBind.ivHeader.setImageResource(R.mipmap.comm_def_header)
            mBind.tvName.setOnClickListener { goActivity(UserLoginActivity::class.java) }
            mBind.ivHeader.setOnClickListener { goActivity(UserLoginActivity::class.java) }
        }
    }

    override fun onLogoutSuccess() {
        showToast(R.string.user_logout_success)
        changeUserStatus()
        EventBus.getDefault().post(UserEvent(UserEvent.TYPE_LOGOUT))
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onUserEvent(event: UserEvent) {
        when (event.type) {
            UserEvent.TYPE_LOGIN -> changeUserStatus()
        }
    }

    override fun supportEventBus() = true
}
