package com.ljb.android.component.knowledge.contract

import com.ljb.android.component.knowledge.bean.KnowMainListBean
import io.reactivex.rxjava3.core.Observable
import mvp.ljb.kt.contract.IModelContract
import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/02/21
 * @Description input description
 **/
interface KnowMainContract {

    interface IView : IViewContract {
        fun onLoadPageError()
        fun onKnowMainListSuccess(data: KnowMainListBean)
    }

    interface IPresenter : IPresenterContract {
        fun getKnowMainList()
    }

    interface IModel : IModelContract {
        fun getKnowMainList(): Observable<KnowMainListBean>
        fun countChildrenName(bean: KnowMainListBean): KnowMainListBean
    }
}
