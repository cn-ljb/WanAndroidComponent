package com.ljb.android.comm.router

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ToastUtils
import com.ljb.android.comm.App
import com.ljb.android.comm.fragment.CommNotFoundFragment
import com.ljb.android.comm.router.service.IAppRouterService
import com.ljb.android.comm.router.service.IHomeRouterService
import com.ljb.android.comm.router.service.IUserRouterService
import com.ljb.android.comm.utils.XLog

object RouterManager {

    fun init(app: Context) {
        getAppService()
        getUserService()
        getHomeService()
    }

    /**
     * 获得Fragment
     * */
    fun getFragment(path: String): Fragment {
        val navigation = ARouter.getInstance().build(path)
            .navigation()
        var fragment: Fragment = CommNotFoundFragment()
        if (navigation != null && navigation is Fragment) {
            fragment = navigation
        }
        return fragment
    }


    /**
     * 跳转Activity
     * */
    fun goActivity(path: String, bundle: Bundle? = null) {
        ARouter.getInstance().build(path)
            .with(bundle)
            .navigation()
    }


    /**
     * 通过ARouter获取User模块数据
     */
    fun getUserService(): IUserRouterService? {
        val service = ARouter.getInstance().build(RouterConfig.Service.USER)
            .navigation()

        if (!checkService(service, IUserRouterService::class.java)) {
            return null
        }

        return service as IUserRouterService
    }

    /**
     * 通过ARouter获取App模块数据
     */
    fun getAppService(): IAppRouterService? {
        val service = ARouter.getInstance().build(RouterConfig.Service.APP)
            .navigation()

        if (!checkService(service, IAppRouterService::class.java)) {
            return null
        }

        return service as IAppRouterService
    }

    /**
     * 通过ARouter获取Home模块数据
     */
    fun getHomeService(): IHomeRouterService? {
        val service = ARouter.getInstance().build(RouterConfig.Service.HOME)
            .navigation()

        if (!checkService(service, IHomeRouterService::class.java)) {
            return null
        }

        return service as IHomeRouterService
    }

    /**
     * 校验服务
     * */
    private fun <I : Class<*>> checkService(service: Any?, superClass: I): Boolean {
        if (service == null) {
            val msg = "not found service: ${superClass.simpleName}"
            XLog.e(msg)
            ToastUtils.showShort(msg)
            return false
        }

        if (!superClass.isAssignableFrom(service::class.java)) {
            val msg = "service found is error:  ${service::class.java.simpleName} not is ${superClass.simpleName}"
            XLog.e(msg)
            ToastUtils.showShort(msg)
            return false
        }
        return true
    }


}