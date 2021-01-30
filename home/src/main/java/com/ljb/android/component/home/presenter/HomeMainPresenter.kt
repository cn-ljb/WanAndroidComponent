package com.ljb.android.component.home.presenter

import com.ljb.android.comm.rx.subscribeNet
import com.ljb.android.component.home.R
import com.ljb.android.component.home.bean.BannerBean
import com.ljb.android.component.home.bean.HomeListBean
import com.ljb.android.component.home.contract.HomeMainContract
import com.ljb.android.component.home.model.HomeMainModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.BiFunction
import mvp.ljb.kt.presenter.BaseMvpPresenter
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

    override fun getBannerAndHomeList(page: Int) {
        Observable.zip(
            getModel().getBanner(),
            getModel().getHomeList(page),
            BiFunction<BannerBean, HomeListBean, List<Any>> { banner, homeList ->
                listOf(banner, homeList)
            }).compose(RxUtils.bindToLife(getMvpView()))
            .compose(RxUtils.schedulerIO2Main())
            .subscribeNet(getMvpView()) {

                onNextEx {
                    getMvpView().onBannerSuccess(it[0] as BannerBean)
                    getMvpView().onHomeListSuccess(it[1] as HomeListBean)
                }

                onErrorEx {
                    getMvpView().onLoadPageError()
                }
            }
    }

    override fun getHomeList(page: Int) {
        getModel().getHomeList(page)
            .compose(RxUtils.bindToLife(getMvpView()))
            .compose(RxUtils.schedulerIO2Main())
            .subscribeNet(getMvpView()) {

                onNextEx {
                    getMvpView().onHomeListSuccess(it)
                }

                onErrorEx {
                    getMvpView().onLoadPageError()
                }
            }
    }

    override fun doCollect(position: Int, id: String) {
        getModel().doCollect(id)
            .compose(RxUtils.bindToLife(getMvpView()))
            .compose(RxUtils.schedulerIO2Main())
            .subscribeNet(getMvpView(), true) {

                onNextEx {
                    if (it.errorCode != "-1") {
                        getMvpView().showToast(R.string.home_collect_success)
                        getMvpView().onCollectSuccess(position)
                    } else {
                        getMvpView().showToast(it.errorMsg)
                    }
                }

                onErrorEx {
                    getMvpView().showToast(R.string.home_collect_error)
                }
            }
    }

}
