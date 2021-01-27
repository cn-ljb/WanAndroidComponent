package com.ljb.android.component.home.contract

import com.ljb.android.comm.mvp.ICommView
import com.ljb.android.component.home.bean.BannerBean
import io.reactivex.rxjava3.core.Observable
import mvp.ljb.kt.contract.IModelContract
import mvp.ljb.kt.contract.IPresenterContract

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/27
 * @Description input description
 **/
interface HomeMainContract {

    interface IView : ICommView {
        fun onBannerSuccess(bannerBean: BannerBean)
    }

    interface IPresenter : IPresenterContract {
        fun getBannerAndList()
    }

    interface IModel : IModelContract {
        fun getBanner():Observable<BannerBean>
    }
}
