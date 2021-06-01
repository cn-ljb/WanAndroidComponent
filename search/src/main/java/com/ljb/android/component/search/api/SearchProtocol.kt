package com.ljb.android.component.search.api

import com.ljb.android.component.search.bean.SearchBean
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Author:Ljb
 * Time:2021/1/23
 * There is a lot of misery in life
 **/
interface SearchProtocol {

//    /**
//     * 搜索文章
//     */
//    @POST("article/query/{page}/json")
//    fun doSearch(
//        @Path("page") page: Int,
//        @Field("k") key: String
//    ): Observable<SearchBean>

    /**
     * 搜索文章 - 协程
     */
    @POST("article/query/{page}/json")
    @FormUrlEncoded
    suspend fun doSearch(
        @Path("page") page: Int,
        @Field("k") key: String
    ): SearchBean


}
