package com.ljb.android.comm.router.service

import android.app.Activity
import com.alibaba.android.arouter.facade.template.IProvider

interface IUserRouterService : IProvider {

    fun getUserInfo(): String

    fun isLogin(activity: Activity, needLogin: Boolean): Boolean

    fun isLoginForResult(activity: Activity, needLogin: Boolean, reqCode: Int): Boolean
}