package com.ljb.android.component.presenter

import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.ljb.android.component.contract.AppHomeContract
import com.ljb.android.component.model.AppHomeModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/23
 * @Description input description
 **/
class AppHomePresenter : BaseMvpPresenter<AppHomeContract.IView, AppHomeContract.IModel>(), AppHomeContract.IPresenter{

    override fun registerModel() = AppHomeModel::class.java

}
