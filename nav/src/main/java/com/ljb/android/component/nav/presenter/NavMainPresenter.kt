package com.ljb.android.component.nav.presenter

import com.ljb.android.comm.rx.subscribeNet
import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.ljb.android.component.nav.contract.NavMainContract
import com.ljb.android.component.nav.model.NavMainModel
import net.ljb.kt.utils.RxUtils

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/02/24
 * @Description input description
 **/
class NavMainPresenter : BaseMvpPresenter<NavMainContract.IView, NavMainContract.IModel>(),
    NavMainContract.IPresenter {

    override fun registerModel() = NavMainModel::class.java

    override fun getNavList() {
        getModel().getNavData()
            .compose(RxUtils.schedulerIO2Main())
            .compose(RxUtils.bindToLife(getMvpView()))
            .subscribeNet(getMvpView()) {
                onNextEx {
                    getMvpView().onNavListSuccess(it)
                }
            }
    }

}
