package com.ljb.android.component.knowledge.presenter

import com.ljb.android.comm.rx.subscribeNet
import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.ljb.android.component.knowledge.contract.KnowMainContract
import com.ljb.android.component.knowledge.model.KnowMainModel
import net.ljb.kt.utils.RxUtils

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/02/21
 * @Description input description
 **/
class KnowMainPresenter : BaseMvpPresenter<KnowMainContract.IView, KnowMainContract.IModel>(), KnowMainContract.IPresenter{

    override fun registerModel() = KnowMainModel::class.java

    override fun getKnowMainList() {
        getModel().getKnowMainList()
            .map {
                getModel().countChildrenName(it)
            }.compose(RxUtils.schedulerIO2Main())
            .compose(RxUtils.bindToLife(getMvpView()))
            .subscribeNet(getMvpView()){

                onNextEx {
                    getMvpView().onKnowMainListSuccess(it)
                }

                onErrorEx {
                    getMvpView().onLoadPageError()
                }
            }

    }

}
