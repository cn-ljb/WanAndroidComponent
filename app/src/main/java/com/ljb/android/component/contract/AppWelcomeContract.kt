package com.ljb.android.component.contract

import io.reactivex.rxjava3.core.Observable
import mvp.ljb.kt.contract.IModelContract
import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract
import java.util.concurrent.TimeUnit

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/23
 * @Description input description
 **/
interface AppWelcomeContract {

    interface IView : IViewContract {
        fun goHome()
    }

    interface IPresenter : IPresenterContract {
        fun delayGoHome(time: Long, unit: TimeUnit)
    }

    interface IModel : IModelContract {
        fun delayGoHome(time: Long, unit: TimeUnit): Observable<Long>
    }
}
