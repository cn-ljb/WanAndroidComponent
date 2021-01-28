package com.ljb.android.comm.router.service

import android.app.Activity
import com.alibaba.android.arouter.facade.template.IProvider

interface IAppRouterService : IProvider {

    fun openOrCloseDrawerLeft(activity: Activity)

}