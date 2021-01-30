package com.ljb.android.component.home.api

import com.ljb.android.component.home.bean.BannerBean
import com.ljb.android.component.home.bean.HomeListBean
import com.ljb.android.component.home.bean.base.HttpBean
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Author:Ljb
 * Time:2021/1/27
 * There is a lot of misery in life
 **/
interface HomeProtocol {

    /**
     * 首页Banner
     */
    @GET("banner/json")
    fun getBanner(): Observable<BannerBean>

    /**
     * 首页文章列表
     * */
    @GET("/article/list/{page}/json")
    fun getHomeList(@Path("page") page: Int): Observable<HomeListBean>

    /**
     * 收藏文章
     * */
    @POST("lg/collect/{id}/json")
    fun doCollect(@Path("id") id: String): Observable<HttpBean>
}
