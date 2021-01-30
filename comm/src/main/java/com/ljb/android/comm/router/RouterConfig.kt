package com.ljb.android.comm.router

object RouterConfig {

    object Activity {
        /**
         * 壳App 主界面
         * */
        const val APP_MAIN = "/app/AppHomeActivity"

    }

    object Fragment {
        /**
         * 壳App 左侧抽屉Fragment
         * */
        const val USER_LEFT_DRAWER = "/user/UserLeftDrawerFragment"
        /**
         * 壳App 首页主页
         * */
        const val HOME_MAIN = "/home/HomeMainFragment"
    }

    object Service {
        /**
         * APP壳模块服务路由
         * */
        const val APP = "/app/AppRouterService"
        /**
         * 用户模块服务路由
         * */
        const val USER = "/user/UserRouterService"
        /**
         * 首页模块服务路由
         * */
        const val HOME = "/user/HomeRouterService"
    }


}