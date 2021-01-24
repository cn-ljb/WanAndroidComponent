package com.ljb.android.comm.rx

import com.blankj.utilcode.util.ToastUtils
import com.ljb.android.comm.R
import com.ljb.android.comm.mvp.ICommView
import com.ljb.android.comm.utils.XLog
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import mvp.ljb.kt.contract.IViewContract

/**
 * 网络请求Rx订阅者
 */
open class BaseNetObserver<T>(var mvpView: IViewContract, var isLoading: Boolean) : Observer<T> {

    private var mOnNextEx: ((T) -> Unit)? = null
    private var mOnErrorEx: ((Throwable) -> Unit)? = null
    private var mOnCompleteEx: (() -> Unit)? = null
    private var mOnSubscribeEx: ((Disposable) -> Unit)? = null

    final override fun onSubscribe(d: Disposable) {
        if (isLoading && mvpView is ICommView) {
            (mvpView as ICommView).showLoading()
        }
        mOnSubscribeEx?.invoke(d)
        onSubscribeEx(d)
    }

    final override fun onNext(response: T) {
        //TODO 可以在此处编写网络请求结果的公共代码
        mOnNextEx?.invoke(response)
        onNextEx(response)
    }

    final override fun onError(e: Throwable) {
        XLog.e(e)
        if (isLoading && mvpView is ICommView) {
            (mvpView as ICommView).hideLoading()
        }
        mOnErrorEx?.invoke(e)
        onErrorEx(e)
        ToastUtils.showShort(R.string.net_error)
    }

    final override fun onComplete() {
        if (isLoading && mvpView is ICommView) {
            (mvpView as ICommView).hideLoading()
        }
        mOnCompleteEx?.invoke()
        onCompleteEx()
    }

    //以下方法提供匿名内部类的形式调用
    protected open fun onCompleteEx() {

    }

    protected open fun onSubscribeEx(d: Disposable) {

    }

    protected open fun onNextEx(data: T) {

    }

    protected open fun onErrorEx(e: Throwable) {

    }


    //以下方法提供Kotlin Lambda调用
    fun onErrorEx(error: (Throwable) -> Unit) {
        mOnErrorEx = error
    }

    fun onNextEx(next: (T) -> Unit) {
        mOnNextEx = next
    }

    fun onCompleteEx(complete: () -> Unit) {
        mOnCompleteEx = complete
    }

    fun onSubscribeEx(subscribe: (Disposable) -> Unit) {
        mOnSubscribeEx = subscribe
    }

}
