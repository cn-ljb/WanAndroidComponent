package com.ljb.android.comm.rx

import android.content.Context
import com.ljb.android.comm.utils.XLog
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

/**
 * 网络请求公共代码
 */
open class BaseNetObserver<T>(var mContext: Context) : Observer<T> {

    private var mOnNextEx: ((T) -> Unit)? = null
    private var mOnErrorEx: ((Throwable) -> Unit)? = null
    private var mOnCompleteEx: (() -> Unit)? = null
    private var mOnSubscribeEx: ((Disposable) -> Unit)? = null

    final override fun onSubscribe(d: Disposable) {
//        onError(IllegalStateException("Not Net Link"))
//        return
        mOnSubscribeEx?.invoke(d)
        onSubscribeEx(d)
    }

    final override fun onNext(response: T) {

        //TODO 公共代码处理


        mOnNextEx?.invoke(response)
        onNextEx(response)
    }

    final override fun onError(e: Throwable) {
        mOnErrorEx?.invoke(e)
        onErrorEx(e)
        XLog.e(e)
    }

    final override fun onComplete() {
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
