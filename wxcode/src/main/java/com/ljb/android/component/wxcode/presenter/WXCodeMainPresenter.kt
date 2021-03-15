package com.ljb.android.component.wxcode.presenter

import com.ljb.android.comm.rx.subscribeNet
import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.ljb.android.component.wxcode.contract.WXCodeMainContract
import com.ljb.android.component.wxcode.model.WXCodeMainModel
import net.ljb.kt.utils.RxUtils

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/02/22
 * @Description input description
 **/
class WXCodeMainPresenter :
    BaseMvpPresenter<WXCodeMainContract.IView, WXCodeMainContract.IModel>(),
    WXCodeMainContract.IPresenter {

    override fun registerModel() = WXCodeMainModel::class.java

    override fun getTabList() {
        getModel().getTabList()
            .compose(RxUtils.schedulerIO2Main())
            .compose(RxUtils.bindToLife(getMvpView()))
            .subscribeNet(getMvpView()) {

                onNextEx {
                    getMvpView().onTabListSuccess(it)
                }

            }
    }

}
