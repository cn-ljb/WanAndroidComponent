package com.ljb.android.component.knowledge.api

import com.ljb.android.component.knowledge.bean.KnowMainListBean
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

/**
 * Author:Ljb
 * Time:2021/1/27
 * There is a lot of misery in life
 **/
interface KnowProtocol {

    /**
     * 知识体系首页列表
     */
    @GET("/tree/json")
    fun getKnowMainList(): Observable<KnowMainListBean>


}
