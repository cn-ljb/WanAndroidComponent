package com.ljb.android.component.presenter

import com.ljb.android.comm.rx.subscribeEx
import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.ljb.android.component.contract.AppWelcomeContract
import com.ljb.android.component.model.AppWelcomeModel
import net.ljb.kt.utils.RxUtils
import java.util.concurrent.TimeUnit

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/23
 * @Description input description
 **/
class AppWelcomePresenter : BaseMvpPresenter<AppWelcomeContract.IView, AppWelcomeContract.IModel>(), AppWelcomeContract.IPresenter{

    override fun registerModel() = AppWelcomeModel::class.java

    override fun delayGoHome(time: Long, unit: TimeUnit) {
        getModel().delayGoHome(time ,  unit)
            .compose(RxUtils.bindToLife(getMvpView()))
            .subscribeEx {
                onNextEx {
                    getMvpView().goHome()
                }
            }
    }

}
