package com.ljb.android.component.nav.contract

import com.ljb.android.component.nav.bean.NavBean
import io.reactivex.rxjava3.core.Observable
import mvp.ljb.kt.contract.IModelContract
import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/02/24
 * @Description input description
 **/
interface NavMainContract {

    interface IView : IViewContract {
        fun onNavListSuccess(data: NavBean)
    }

    interface IPresenter : IPresenterContract {
        fun getNavList()
    }

    interface IModel : IModelContract {
        fun getNavData(): Observable<NavBean>
    }
}
