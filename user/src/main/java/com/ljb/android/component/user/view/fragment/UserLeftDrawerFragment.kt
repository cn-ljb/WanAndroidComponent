package com.ljb.android.component.user.view.fragment

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.ljb.android.comm.common.LocUser
import com.ljb.android.comm.eventbus.UserEvent
import com.ljb.android.comm.img.ImageLoader
import com.ljb.android.comm.img.format.ImgFormatEvent
import com.ljb.android.comm.mvp.CommMvpFragment
import com.ljb.android.comm.router.RouterConfig
import com.ljb.android.comm.utils.DefResUtils
import com.ljb.android.component.user.R
import com.ljb.android.component.user.contract.UserLeftDrawerContract
import com.ljb.android.component.user.databinding.FragmentUserLeftDrawerBinding
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
    CommMvpFragment<UserLeftDrawerContract.IPresenter, FragmentUserLeftDrawerBinding>(),
    UserLeftDrawerContract.IView, View.OnClickListener {

    override fun registerPresenter() = UserLeftDrawerPresenter::class.java

    override fun getLayoutId() = R.layout.fragment_user_left_drawer

    override fun registerBinding(): FragmentUserLeftDrawerBinding {
        return FragmentUserLeftDrawerBinding.inflate(layoutInflater, mParentView, false)
    }

    override fun initStatusBar() {
        ImmersionBar.with(this)
            .transparentStatusBar()     //透明状态栏，不写默认透明色
            .statusBarDarkFont(false) //状态栏字体是深色，不写默认为亮色
            .navigationBarDarkIcon(false) //导航栏图标是深色，不写默认为亮色
            .init()
    }

    override fun initView() {
        mBind.btnLogout.setOnClickListener(this)
        initMenuView()
    }

    override fun initData() {
        changeUserStatus()
    }

    fun initMenuView() {
        mBind.llCollect.setOnClickListener(this)
        mBind.llWeb.setOnClickListener(this)
        mBind.llAbout.setOnClickListener(this)
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
            mBind.tvName.setOnClickListener(this)
            mBind.ivHeader.setOnClickListener(this)
        }
    }

    override fun onLogoutSuccess() {
        showToast(R.string.user_logout_success)
        changeUserStatus()
        EventBus.getDefault().post(
            UserEvent(
                UserEvent.EventType.TYPE_LOGOUT
            )
        )
    }

    override fun supportEventBus() = true

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onUserEvent(event: UserEvent) {
        when (event.type) {
            UserEvent.EventType.TYPE_LOGOUT,
            UserEvent.EventType.TYPE_LOGIN -> {
                changeUserStatus()
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_logout -> {
                getPresenter().logout()
            }
            R.id.tv_name,
            R.id.iv_header -> {
                goActivity(UserLoginActivity::class.java)
            }
            R.id.ll_collect -> {
                //TODO go collect page
                showToast(R.string.comm_wait_develop)
            }
            R.id.ll_web -> {
                //TODO go comm web page
                showToast(R.string.comm_wait_develop)
            }
            R.id.ll_about -> {
                //TODO go about page
                showToast(R.string.comm_wait_develop)
            }

        }
    }


}
