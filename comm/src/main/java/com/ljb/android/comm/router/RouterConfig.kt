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
         * 首页模块主页
         * */
        const val HOME_MAIN = "/home/HomeMainFragment"

        /**
         * 知识体系模块主页
         */
        const val KNOW_MAIN = "/know/KnowMainFragment"

        /**
         * 公众号模块主页
         * */
        const val WECHAT_CODE_MAIN = "/wechat/WeChatCodeMainFragment"

        /**
         * 导航模块主页
         */
        const val NAV_MAIN = "/nav/NavMainFragment"

        /**
         * 项目模块主页
         */
        const val PROJECT_MAIN = "/project/ProjectMainFragment"
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

        /**
         * 公众号 服务路由
         * */
        const val WECHAT_CODE = "/wechat/WXCodeRouterService"

        /**
         * 导航 服务路由
         */
        const val NAV = "/nav/NavRouterService"

        /**
         * 项目 服务路由
         * */
        const val PROJECT = "/project/ProjectRouterService"
    }


}