package com.ljb.android.comm.rx

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.core.Observable
import mvp.ljb.kt.contract.IViewContract

/**
 * 普通Rx订阅
 * @param func  订阅回调
 **/
fun <T> Observable<T>.subscribeEx(func: (BaseObserver<T>.() -> Unit)) {
    subscribe(BaseObserver<T>().apply(func))
}

/**
 * 网络Rx订阅
 * @param mvpView   MVP架构View层对象
 * @param isLoading 是否显示loading
 * @param func      订阅回调
 * */
fun <T> Observable<T>.subscribeNet(
    mvpView: IViewContract,
    isLoading: Boolean = false,
    func: (BaseNetObserver<T>.() -> Unit)
) {
    subscribe(BaseNetObserver<T>(mvpView, isLoading).apply(func))
}

/**
 * MvpView 转 Context
 */
fun IViewContract.getContext(): Context {
    return when (this) {
        is Activity -> this
        is Fragment -> (this as Fragment).activity!!
        else -> throw IllegalStateException("the mvpView not found context")
    }
}



