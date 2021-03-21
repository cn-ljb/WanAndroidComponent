package com.ljb.android.comm.router.service

import com.alibaba.android.arouter.facade.template.IProvider

interface IChatRouterService : IProvider {

    fun loginSocket(json: String)

    fun logoutSocket()
}