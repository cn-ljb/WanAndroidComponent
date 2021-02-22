package com.ljb.android.component.wechatcode.api

import com.ljb.android.comm.bean.base.HttpBean
import com.ljb.android.component.wechatcode.bean.WCodeTabBean
import com.ljb.android.component.wechatcode.bean.WXArticleListBean
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

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

    /**
     * 公众号的文章列表
     * */
    @GET("/wxarticle/list/{id}/{page}/json")
    fun getArticleList(
        @Path("id") id: String,
        @Path("page") page: Int
    ): Observable<WXArticleListBean>


    /**
     * 收藏文章
     * */
    @POST("lg/collect/{id}/json")
    fun doCollect(@Path("id") id: String): Observable<HttpBean>

    /***
     * 取消收藏
     */
    @POST("lg/uncollect_originId/{id}/json")
    fun cancelCollect(@Path("id") id: String): Observable<HttpBean>

}
