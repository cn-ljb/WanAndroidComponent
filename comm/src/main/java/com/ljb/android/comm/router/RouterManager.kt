package com.ljb.android.comm.router

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ToastUtils
import com.ljb.android.comm.fragment.CommNotFoundFragment
import com.ljb.android.comm.router.service.*
import com.ljb.android.comm.utils.XLog

object RouterManager {

    fun init(app: Context) {
        getAppService()
        getUserService()
        getHomeService()
        getKnowledgeService()
        getWeChatCodeService()
        getNavService()
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
            val msg =
                "service found is error:  ${service::class.java.simpleName} not is ${superClass.simpleName}"
            XLog.e(msg)
            ToastUtils.showShort(msg)
            return false
        }
        return true
    }


    /**
     * 获取App模块服务
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
     * 获取User模块服务 - 用户信息
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
     * 获取Home模块服务 - 首页
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
     * 获取Know模块服务 - 知识体系
     * */
    fun getKnowledgeService(): IKnowRouterService? {

        val service = ARouter.getInstance().build(RouterConfig.Service.KNOW)
            .navigation()

        if (!checkService(service, IKnowRouterService::class.java)) {
            return null
        }

        return service as IKnowRouterService

    }

    /**
     * 获取WeChatCode模块服务 - 公众号
     * */
    fun getWeChatCodeService(): IWeChatCodeRouterService? {

        val service = ARouter.getInstance().build(RouterConfig.Service.WECHAT_CODE)
            .navigation()

        if (!checkService(service, IWeChatCodeRouterService::class.java)) {
            return null
        }

        return service as IWeChatCodeRouterService

    }

    /**
     * 获取Nav模块服务 - 公众号
     * */
    fun getNavService(): INavRouterService? {

        val service = ARouter.getInstance().build(RouterConfig.Service.NAV)
            .navigation()

        if (!checkService(service, INavRouterService::class.java)) {
            return null
        }

        return service as INavRouterService

    }


}