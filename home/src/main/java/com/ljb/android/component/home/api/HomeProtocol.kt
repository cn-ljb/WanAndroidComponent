package com.ljb.android.component.home.api

import com.ljb.android.component.home.bean.BannerBean
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

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

}
