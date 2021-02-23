package com.ljb.android.component.wechatcode.contract

import com.ljb.android.comm.bean.base.HttpBean
import com.ljb.android.component.wechatcode.bean.WXArticleListBean
import io.reactivex.rxjava3.core.Observable
import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract
import mvp.ljb.kt.contract.IModelContract

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/02/22
 * @Description input description
 **/
interface WXArticleListContract {

    interface IView : IViewContract {
        fun onArticleListSuccess(data: WXArticleListBean)
        fun onCollectStatus(position: Int, status: Boolean)
    }

    interface IPresenter : IPresenterContract {
        fun getArticleList(id: String, page: Int)
        fun cancelCollect(position: Int, id: String)
        fun doCollect(position: Int, id: String)
    }

    interface IModel : IModelContract {
        fun getArticleList(id: String, page: Int): Observable<WXArticleListBean>
        fun cancelCollect(id: String): Observable<HttpBean>
        fun doCollect(id: String): Observable<HttpBean>
    }
}
