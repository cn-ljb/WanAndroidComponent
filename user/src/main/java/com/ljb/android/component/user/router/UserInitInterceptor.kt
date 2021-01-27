package com.ljb.android.component.user.router

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor
import com.ljb.android.comm.utils.XLog

/**
 * Author:Ljb
 * Time:2021/1/27
 * User模块拦截器初始化
 **/
@Interceptor(priority = 1 ,  name = "UserInitInterceptor")
class UserInitInterceptor : IInterceptor {

    override fun process(postcard: Postcard?, callback: InterceptorCallback?) {
        callback?.onContinue(postcard)
    }

    override fun init(context: Context?) {
        // 拦截器的初始化，会在sdk初始化的时候调用该方法，仅会调用一次
        // TODO 在此处编写模块自身的初始化代码
        XLog.i("UserInitInterceptor init")
    }
}