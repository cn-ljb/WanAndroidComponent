package com.ljb.android.comm.router.service

import android.content.Context
import com.alibaba.android.arouter.facade.template.IProvider

interface IUserRouterService : IProvider {

    fun getUserInfo(context: Context): String

    fun isLogin(context: Context, needLogin: Boolean): Boolean
}