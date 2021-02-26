package com.ljb.android.component.project.api

import com.ljb.android.component.project.bean.ProjectTabBean
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

/**
 * Author:Ljb
 * Time:2021/1/23
 * There is a lot of misery in life
 **/
interface ProjectProtocol {

    /**
     * 获取项目Tabs接口
     */
    @GET("project/tree/json")
    fun getProjectTabList(): Observable<ProjectTabBean>


}
