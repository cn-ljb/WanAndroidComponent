package com.ljb.android.component.home.presenter

import com.ljb.android.comm.rx.subscribeNet
import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.ljb.android.component.home.contract.HomeMainContract
import com.ljb.android.component.home.model.HomeMainModel
import net.ljb.kt.utils.RxUtils

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/27
 * @Description input description
 **/
class HomeMainPresenter :
    BaseMvpPresenter<HomeMainContract.IView, HomeMainContract.IModel>(),
    HomeMainContract.IPresenter {

    override fun registerModel() = HomeMainModel::class.java

    override fun getBannerAndList() {
        getModel().getBanner()
            .compose(RxUtils.bindToLife(getMvpView()))
            .compose(RxUtils.schedulerIO2Main())
            .subscribeNet(getMvpView(), true) {

            }
    }

}
