package com.ljb.android.component.home.contract

import com.ljb.android.comm.mvp.ICommView
import com.ljb.android.component.home.bean.BannerBean
import com.ljb.android.component.home.bean.HomeListBean
import com.ljb.android.component.home.bean.base.HttpBean
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
        fun onHomeListSuccess( homeListBean: HomeListBean)
        fun onLoadPageError()
        fun onCollectSuccess(position: Int)
    }

    interface IPresenter : IPresenterContract {
        fun getBannerAndHomeList(page: Int)
        fun getHomeList(page: Int)
        fun doCollect(position:Int , id: String)
    }

    interface IModel : IModelContract {
        fun getBanner():Observable<BannerBean>
        fun getHomeList(page: Int): Observable<HomeListBean>
        fun doCollect(id: String): Observable<HttpBean>
    }
}
