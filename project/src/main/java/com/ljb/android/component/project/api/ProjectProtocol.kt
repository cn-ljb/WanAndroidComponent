package com.ljb.android.component.project.api

import com.ljb.android.component.project.bean.ProjectListBean
import com.ljb.android.component.project.bean.ProjectTabBean
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

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

    /**
     * 获取项目列表接口
     * */
    @GET("project/list/{page}/json")
    fun getProjectList(
        @Path("page") page: Int,
        @Query("cid") cid: String
    ): Observable<ProjectListBean>

}
