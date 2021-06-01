package com.ljb.android.comm.router

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.ljb.android.comm.fragment.CommNotFoundFragment
import com.ljb.android.comm.router.service.*
import com.ljb.android.comm.utils.XLog

object RouterManager {

    fun init() {
        getAppService()
        getUserService()
        getHomeService()
        getKnowService()
        getWXCodeService()
        getNavService()
        getProjectService()
        getSearchService()
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
     * 跳转 Activity
     * */
    fun goActivity(path: String, bundle: Bundle? = null) {
        ARouter.getInstance().build(path)
            .with(bundle)
            .navigation()
    }

    /**
     * 跳转 ActivityForResult
     * */
    fun goActivityForResult(
        path: String,
        bundle: Bundle? = null,
        activity: Activity,
        requestCode: Int
    ) {
        ARouter.getInstance().build(path)
            .with(bundle)
            .navigation(activity, requestCode)
    }


    /**
     * 校验服务
     * */
    private fun <I : Class<*>> checkService(service: Any?, superClass: I): Boolean {
        if (service == null) {
            val msg = "not found service: ${superClass.simpleName}"
            XLog.e(msg)
            return false
        }

        if (!superClass.isAssignableFrom(service::class.java)) {
            val msg =
                "service found is error:  ${service::class.java.simpleName} not is ${superClass.simpleName}"
            XLog.e(msg)
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
    fun getKnowService(): IKnowRouterService? {

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
    fun getWXCodeService(): IWXCodeRouterService? {
        val service = ARouter.getInstance().build(RouterConfig.Service.WECHAT_CODE)
            .navigation()
        if (!checkService(service, IWXCodeRouterService::class.java)) {
            return null
        }
        return service as IWXCodeRouterService
    }

    /**
     * 获取Nav模块服务 - 导航
     * */
    fun getNavService(): INavRouterService? {
        val service = ARouter.getInstance().build(RouterConfig.Service.NAV)
            .navigation()

        if (!checkService(service, INavRouterService::class.java)) {
            return null
        }

        return service as INavRouterService
    }

    /**
     * 获取Project模块服务 - 项目
     * */
    fun getProjectService(): IProjectRouterService? {
        val service = ARouter.getInstance().build(RouterConfig.Service.PROJECT)
            .navigation()

        if (!checkService(service, IProjectRouterService::class.java)) {
            return null
        }

        return service as IProjectRouterService
    }

    /**
     * 获取Search模块服务 - 搜索
     * */
    fun getSearchService(): ISearchRouterService? {
        val service = ARouter.getInstance().build(RouterConfig.Service.SEARCH)
            .navigation()

        if (!checkService(service, ISearchRouterService::class.java)) {
            return null
        }

        return service as ISearchRouterService
    }

}