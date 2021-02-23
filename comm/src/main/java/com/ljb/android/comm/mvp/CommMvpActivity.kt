package com.ljb.android.comm.mvp

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.viewbinding.ViewBinding
import com.gyf.immersionbar.ImmersionBar
import com.ljb.android.comm.R
import com.ljb.android.comm.databinding.CommLayoutLoadingDialogBinding
import com.ljb.android.comm.databinding.CommLayoutTitlebarBinding
import mvp.ljb.kt.act.BaseMvpAppCompatActivity
import mvp.ljb.kt.contract.IPresenterContract
import org.greenrobot.eventbus.EventBus


/**
 * Author:Ljb
 * Time:2019/7/4
 * There is a lot of misery in life
 **/
abstract class CommMvpActivity<out P : IPresenterContract, B : ViewBinding> :
    BaseMvpAppCompatActivity<P>(), ICommView {

    /**
     * 父View
     * */
    protected var mParentView: RelativeLayout? = null

    /**
     * Title布局 ViewBind
     * @需要: supportTitle() return true
     * */
    protected lateinit var mBindTitleBar: CommLayoutTitlebarBinding

    /**
     * Loading布局 ViewBind
     * */
    protected lateinit var mBindLoading: CommLayoutLoadingDialogBinding


    /**
     * 业务布局 ViewBind
     * */
    protected lateinit var mBind: B

    abstract fun registerBinding(): B

    override fun onCreate(savedInstanceState: Bundle?) {
        initStatusBar()
        super.onCreate(savedInstanceState)
        initOther()
    }

    override fun initContentView() {
        // parentView
        mParentView = RelativeLayout(this)

        // content view
        mBind = registerBinding()

        // title view
        if (supportTitle()) {

            initTitleBar(layoutInflater)

            val layoutParams = mBind.root.layoutParams as RelativeLayout.LayoutParams
            layoutParams.addRule(RelativeLayout.BELOW, R.id.layout_toolbar)
            mParentView?.addView(mBind.root, layoutParams)
        } else {
            mParentView?.addView(mBind.root)
        }

        //loading view
        initLoadingView(layoutInflater)

        setContentView(mParentView)

    }

    private fun initLoadingView(inflater: LayoutInflater) {
        mBindLoading = CommLayoutLoadingDialogBinding.inflate(inflater, mParentView, false)
        mParentView?.addView(mBindLoading.root)
    }

    private fun initTitleBar(inflater: LayoutInflater) {
        mBindTitleBar = CommLayoutTitlebarBinding.inflate(inflater, mParentView, false)
        mBindTitleBar.ivToolbarLeft.setOnClickListener { onBackPressed() }
        mParentView?.addView(mBindTitleBar.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        unOther()
    }

    private fun initOther() {
        if (supportEventBus() && !EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    private fun unOther() {
        if (supportEventBus() && EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }

    private fun setLoading(isShow: Boolean) {
        mBindLoading.root.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    override fun showLoading() {
        setLoading(true)
    }

    override fun hideLoading() {
        setLoading(false)
    }

    protected fun setTitleText(
        @StringRes resId: Int,
        @ColorInt colorId: Int = resources.getColor(R.color.color_333)
    ) {
        if (supportTitle()) {
            mBindTitleBar.tvToolbarCenter.setText(resId)
            mBindTitleBar.tvToolbarCenter.setTextColor(colorId)
        }
    }

    protected fun setTitleText(
        text: String,
        @ColorInt colorId: Int = resources.getColor(R.color.color_333)
    ) {
        if (supportTitle()) {
            mBindTitleBar.tvToolbarCenter.text = text
            mBindTitleBar.tvToolbarCenter.setTextColor(colorId)
        }
    }

    protected fun setTitleLeftImage(@DrawableRes resId: Int) {
        if (supportTitle()) {
            mBindTitleBar.ivToolbarLeft.setImageResource(resId)
        }
    }

    protected fun setTitleLeftImage(@DrawableRes resId: Int, listener: View.OnClickListener) {
        if (supportTitle()) {
            mBindTitleBar.ivToolbarLeft.setImageResource(resId)
            mBindTitleBar.ivToolbarLeft.setOnClickListener(listener)
        }
    }

    protected fun setTitleRightImage(@DrawableRes resId: Int, listener: View.OnClickListener) {
        if (supportTitle()) {
            mBindTitleBar.ivToolbarRight.setImageResource(resId)
            mBindTitleBar.ivToolbarRight.setOnClickListener(listener)
            if (View.VISIBLE != mBindTitleBar.ivToolbarRight.visibility) {
                mBindTitleBar.ivToolbarRight.visibility = View.VISIBLE
            }
        }
    }

    protected fun setTitleRightText(@StringRes resId: Int, listener: View.OnClickListener) {
        setTitleRightText(getText(resId), listener)
    }

    protected fun setTitleRightText(text: CharSequence, listener: View.OnClickListener) {
        if (supportTitle()) {
            mBindTitleBar.tvToolbarRight.text = text
            mBindTitleBar.tvToolbarRight.setOnClickListener(listener)
            if (View.VISIBLE != mBindTitleBar.tvToolbarRight.visibility) {
                mBindTitleBar.tvToolbarRight.visibility = View.VISIBLE
            }
        }
    }


    protected open fun initStatusBar() {
        ImmersionBar.with(this)
            .transparentStatusBar()     //透明状态栏，不写默认透明色
            .statusBarDarkFont(true) //状态栏字体是深色，不写默认为亮色
            .navigationBarDarkIcon(true) //导航栏图标是深色，不写默认为亮色
            .init()
//            .transparentStatusBar() //透明状态栏，不写默认透明色
//            .transparentNavigationBar() //透明导航栏，不写默认黑色(设置此方法，fullScreen()方法自动为true)
//            .transparentBar() //透明状态栏和导航栏，不写默认状态栏为透明色，导航栏为黑色（设置此方法，fullScreen()方法自动为true）
//            .statusBarColor(android.R.color.transparent) //状态栏颜色，不写默认透明色
//            .navigationBarColor(android.R.color.black) //导航栏颜色，不写默认黑色
//            .barColor(android.R.color.black) //同时自定义状态栏和导航栏颜色，不写默认状态栏为透明色，导航栏为黑色
//            .statusBarAlpha(0.3f) //状态栏透明度，不写默认0.0f
//            .navigationBarAlpha(0.4f) //导航栏透明度，不写默认0.0F
//            .barAlpha(0.3f) //状态栏和导航栏透明度，不写默认0.0f
//            .statusBarDarkFont(true) //状态栏字体是深色，不写默认为亮色
//            .navigationBarDarkIcon(true) //导航栏图标是深色，不写默认为亮色
//            .autoDarkModeEnable(true) //自动状态栏字体和导航栏图标变色，必须指定状态栏颜色和导航栏颜色才可以自动变色哦
//            .autoStatusBarDarkModeEnable(true, 0.2f) //自动状态栏字体变色，必须指定状态栏颜色才可以自动变色哦
//            .autoNavigationBarDarkModeEnable(true, 0.2f) //自动导航栏图标变色，必须指定导航栏颜色才可以自动变色哦
//            .flymeOSStatusBarFontColor(android.R.color.black) //修改flyme OS状态栏字体颜色
//            .fullScreen(true) //有导航栏的情况下，activity全屏显示，也就是activity最下面被导航栏覆盖，不写默认非全屏
//            .hideBar(BarHide.FLAG_HIDE_BAR) //隐藏状态栏或导航栏或两者，不写默认不隐藏
//            .addViewSupportTransformColor(titleView) //设置支持view变色，可以添加多个view，不指定颜色，默认和状态栏同色，还有两个重载方法
//            .titleBar(titleView) //解决状态栏和布局重叠问题，任选其一
//            .titleBarMarginTop(titleView) //解决状态栏和布局重叠问题，任选其一
//            .statusBarView(titleView) //解决状态栏和布局重叠问题，任选其一
//            .fitsSystemWindows(true) //解决状态栏和布局重叠问题，任选其一，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色，还有一些重载方法
//            .supportActionBar(true) //支持ActionBar使用
//            .statusBarColorTransform(android.R.color.black) //状态栏变色后的颜色
//            .navigationBarColorTransform(android.R.color.black) //导航栏变色后的颜色
//            .barColorTransform(android.R.color.black) //状态栏和导航栏变色后的颜色
//            .removeSupportView(titleView) //移除指定view支持
//            .removeSupportAllView() //移除全部view支持
//            .navigationBarEnable(true) //是否可以修改导航栏颜色，默认为true
//            .navigationBarWithKitkatEnable(true) //是否可以修改安卓4.4和emui3.x手机导航栏颜色，默认为true
//            .navigationBarWithEMUI3Enable(true) //是否可以修改emui3.x手机导航栏颜色，默认为true
//            .keyboardEnable(true) //解决软键盘与底部输入框冲突问题，默认为false，还有一个重载方法，可以指定软键盘mode
//            .keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE) //单独指定软键盘模式
//            .setOnKeyboardListener(OnKeyboardListener { isPopup, keyboardHeight ->
//                //软键盘监听回调，keyboardEnable为true才会回调此方法
//                //isPopup为true，软键盘弹出，为false，软键盘关闭
//            })
//            .setOnNavigationBarListener(onNavigationBarListener) //导航栏显示隐藏监听，目前只支持华为和小米手机
//            .setOnBarListener(OnBarChangeListener) //第一次调用和横竖屏切换都会触发，可以用来做刘海屏遮挡布局控件的问题
//            .addTag("tag") //给以上设置的参数打标记
//            .getTag("tag") //根据tag获得沉浸式参数
//            .reset() //重置所以沉浸式参数
//            .init() //必须调用方可应用以上所配置的参数
    }

    override fun getResources(): Resources {
        val res = super.getResources()
        val newConfig = Configuration()
        newConfig.setToDefaults()
        res.updateConfiguration(newConfig, res.displayMetrics)
        return res
    }

    protected open fun supportEventBus() = false

    protected open fun supportTitle() = false

}