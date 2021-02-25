package com.ljb.android.component.nav.contract

import com.ljb.android.component.nav.bean.NavBean
import io.reactivex.rxjava3.core.Observable
import mvp.ljb.kt.contract.IModelContract
import mvp.ljb.kt.contract.IPresenterContract
import com.ljb.android.comm.mvp.ICommView

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/02/24
 * @Description input description
 **/
interface NavMainContract {

    interface IView : ICommView {
        fun onNavListSuccess(data: NavBean)
    }

    interface IPresenter : IPresenterContract {
        fun getNavList()
    }

    interface IModel : IModelContract {
        fun getNavData(): Observable<NavBean>
    }
}
