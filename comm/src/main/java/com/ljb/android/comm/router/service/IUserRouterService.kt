package com.ljb.android.comm.router.service

import android.content.Context
import com.alibaba.android.arouter.facade.template.IProvider
import com.ljb.android.comm.bean.UserBean

interface IUserRouterService : IProvider {

    fun getUserInfo(context: Context): UserBean
}