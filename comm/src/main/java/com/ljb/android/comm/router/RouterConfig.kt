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
         * 左侧抽屉Fragment
         * */
        const val USER_LEFT_DRAWER = "/user/UserLeftDrawerFragment"
        /**
         * 首页主页
         * */
        const val HOME_MAIN = "/home/HomeMainFragment"

        /**
         * 知识体系主页
         */
        const val KNOW_MAIN = "/know/KnowMainFragment"

    }

    object Service {
        /**
         * APP壳模块 服务路由
         * */
        const val APP = "/app/AppRouterService"
        /**
         * 用户模块 服务路由
         * */
        const val USER = "/user/UserRouterService"
        /**
         * 首页模块 服务路由
         * */
        const val HOME = "/home/HomeRouterService"
        /**
         * 知识体系模块 服务路由
         */
        const val KNOW = "/know/KnowRouterService"
    }


}