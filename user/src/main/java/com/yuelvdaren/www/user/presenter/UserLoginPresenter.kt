package com.yuelvdaren.www.user.presenter

import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.yuelvdaren.www.user.contract.UserLoginContract
import com.yuelvdaren.www.user.model.UserLoginModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/23
 * @Description input description
 **/
class UserLoginPresenter : BaseMvpPresenter<UserLoginContract.IView, UserLoginContract.IModel>(), UserLoginContract.IPresenter{

    override fun registerModel() = UserLoginModel::class.java

}
