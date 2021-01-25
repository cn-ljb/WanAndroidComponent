package com.ljb.android.comm.router

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.ljb.android.comm.fragment.CommNotFindFragment
import com.ljb.android.comm.router.service.IUserRouterService

object RouterManager {

    /**
     *  通过ARouter获取User模块数据
     */
    fun getUserService(): IUserRouterService {
//        val navigation = ARouter.getInstance().build(RouterConfig.Service.USER_GET_USER_INFO)
//            .navigation()
//        var service: IUserRouterService? = null
//        if (navigation != null && navigation is IUserRouterService) {
//            service = navigation
//        }
//        return service
        return ARouter.getInstance().build(RouterConfig.Service.USER_GET_USER_INFO)
            .navigation() as IUserRouterService
    }

    /**
     * 获得Fragment
     * */
    fun getFragment(path: String): Fragment {
        val navigation = ARouter.getInstance().build(path)
            .navigation()
        var fragment: Fragment = CommNotFindFragment()
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

}