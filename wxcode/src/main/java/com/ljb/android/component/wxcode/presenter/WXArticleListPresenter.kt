package com.ljb.android.component.wxcode.presenter

import com.ljb.android.comm.rx.subscribeNet
import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.ljb.android.component.wxcode.contract.WXArticleListContract
import com.ljb.android.component.wxcode.model.WXArticleListModel
import net.ljb.kt.utils.RxUtils

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/02/22
 * @Description input description
 **/
class WXArticleListPresenter :
    BaseMvpPresenter<WXArticleListContract.IView, WXArticleListContract.IModel>(),
    WXArticleListContract.IPresenter {

    override fun registerModel() = WXArticleListModel::class.java


    override fun getArticleList(id: String, page: Int) {
        getModel().getArticleList(id, page)
            .compose(RxUtils.schedulerIO2Main())
            .compose(RxUtils.bindToLife(getMvpView()))
            .subscribeNet(getMvpView()) {

                onNextEx {
                    getMvpView().onArticleListSuccess(it)
                }

                onErrorEx {
                    getMvpView().onArticleListError()
                }
            }
    }

    override fun cancelCollect(position: Int, id: String) {
        getModel().cancelCollect(id)
            .compose(RxUtils.bindToLife(getMvpView()))
            .compose(RxUtils.schedulerIO2Main())
            .subscribeNet(getMvpView(), true) {
                onNextEx {
                    getMvpView().onCollectStatus(position, false)
                }
            }
    }

    override fun doCollect(position: Int, id: String) {
        getModel().doCollect(id)
            .compose(RxUtils.bindToLife(getMvpView()))
            .compose(RxUtils.schedulerIO2Main())
            .subscribeNet(getMvpView(), true) {
                onNextEx {
                    getMvpView().onCollectStatus(position, true)
                }
            }
    }

}
