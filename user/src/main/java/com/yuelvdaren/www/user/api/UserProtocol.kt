package com.yuelvdaren.www.user.api

import com.yuelvdaren.www.user.bean.LoginBean
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Author:Ljb
 * Time:2021/1/23
 * There is a lot of misery in life
 **/
interface UserProtocol{

    /**
     * 注册
     * http://www.wanandroid.com/user/register
     *
     * @param username user name
     * @param password password
     * @param repassword re password
     * @return 注册数据
     */
    @POST("user/register")
    @FormUrlEncoded
    fun getRegisterData(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): Observable<LoginBean>

}
