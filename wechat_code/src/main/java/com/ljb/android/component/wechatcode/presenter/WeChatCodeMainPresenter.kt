package com.ljb.android.component.wechatcode.presenter

import com.ljb.android.comm.rx.subscribeNet
import com.ljb.android.component.wechatcode.R
import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.ljb.android.component.wechatcode.contract.WeChatCodeMainContract
import com.ljb.android.component.wechatcode.model.WeChatCodeMainModel
import net.ljb.kt.utils.RxUtils

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/02/22
 * @Description input description
 **/
class WeChatCodeMainPresenter :
    BaseMvpPresenter<WeChatCodeMainContract.IView, WeChatCodeMainContract.IModel>(),
    WeChatCodeMainContract.IPresenter {

    override fun registerModel() = WeChatCodeMainModel::class.java

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
