package com.ljb.android.component.user.bean

/**
 * Author:Ljb
 * Time:2021/1/23
 * There is a lot of misery in life
 **/
class LoginBean(var data: DataBean) : HttpBean() {

    class DataBean(var id: String, var nickname: String, var icon: String)

}
