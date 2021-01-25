package com.ljb.android.component.user.presenter

import com.ljb.android.comm.rx.subscribeNet
import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.ljb.android.component.user.contract.UserLeftDrawerContract
import com.ljb.android.component.user.model.UserLeftDrawerModel
import net.ljb.kt.utils.RxUtils

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/25
 * @Description input description
 **/
class UserLeftDrawerPresenter :
    BaseMvpPresenter<UserLeftDrawerContract.IView, UserLeftDrawerContract.IModel>(),
    UserLeftDrawerContract.IPresenter {

    override fun registerModel() = UserLeftDrawerModel::class.java

    override fun logout() {
        getModel().logout()
            .map { getModel().clearUserToSP(it) }
            .compose(RxUtils.bindToLife(getMvpView()))
            .compose(RxUtils.schedulerIO2Main())
            .subscribeNet(getMvpView(), true) {
                onNextEx {
                    getMvpView().onLogoutSuccess()
                }
            }
    }

}
