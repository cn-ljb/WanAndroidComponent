package com.ljb.android.component.user.api

import com.ljb.android.component.user.bean.HttpBean
import com.ljb.android.component.user.bean.LoginBean
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Author:Ljb
 * Time:2021/1/23
 * There is a lot of misery in life
 **/
interface UserProtocol {

    /**
     * 注册
     * https://www.wanandroid.com/user/register
     *
     * @param username user name
     * @param password password
     * @param repassword re password
     * @return 注册数据
     */
    @FormUrlEncoded
    @POST("user/register")
    fun registerUser(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): Observable<LoginBean>


    /**
     * 登录
     * */
    @FormUrlEncoded
    @POST("user/login")
    fun loginUser(
        @Field("username") userName: String,
        @Field("password") pwd: String
    ): Observable<LoginBean>

    /**
     * 登出
     */
    @GET("user/logout/json")
    fun logoutUser(): Observable<HttpBean>

}
