package com.ljb.android.component.contract

import com.ljb.android.comm.mvp.ICommView
import io.reactivex.rxjava3.core.Observable
import mvp.ljb.kt.contract.IModelContract
import mvp.ljb.kt.contract.IPresenterContract
import java.util.concurrent.TimeUnit

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/23
 * @Description input description
 **/
interface AppWelcomeContract {

    interface IView : ICommView {
        fun goHome()
        fun setWelcomeText(result: String)
    }

    interface IPresenter : IPresenterContract {
        fun delayGoHome(time: Long, unit: TimeUnit)
        fun timer(time: Long, unit: TimeUnit)
    }

    interface IModel : IModelContract {
        fun delayGoHome(time: Long, unit: TimeUnit): Observable<Long>
        fun timer(time: Long, unit: TimeUnit): Observable<Long>
        fun getText(): String
    }
}
