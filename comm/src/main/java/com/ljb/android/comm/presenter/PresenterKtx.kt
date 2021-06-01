package com.ljb.android.comm.presenter

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import mvp.ljb.kt.presenter.BaseMvpPresenter


fun BaseMvpPresenter<*, *>.getLifecycle(): LifecycleOwner {

    val mvpView = getMvpView()
    if (mvpView is LifecycleOwner) {
        return mvpView
    } else {
        throw IllegalStateException("not found Lifecycle")
    }
}

val BaseMvpPresenter<*, *>.presenterScope: LifecycleCoroutineScope
    get() {
        val mvpView = getMvpView()
        if (mvpView is LifecycleOwner) {
            return mvpView.lifecycleScope
        } else {
            throw IllegalStateException("not found Lifecycle")
        }
    }
