package com.ljb.android.component.wechatcode.api

import com.ljb.android.component.wechatcode.bean.WCodeTabBean
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

/**
 * Author:Ljb
 * Time:2021/1/27
 * There is a lot of misery in life
 **/
interface WeChatCodeProtocol {

    /**
     * 知识体系首页列表
     */
    @GET("/wxarticle/chapters/json")
    fun getWeChatTabList(): Observable<WCodeTabBean>


}
