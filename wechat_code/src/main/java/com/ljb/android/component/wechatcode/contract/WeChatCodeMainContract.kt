package com.ljb.android.component.wechatcode.contract

import com.ljb.android.comm.mvp.ICommView
import com.ljb.android.component.wechatcode.bean.WCodeTabBean
import io.reactivex.rxjava3.core.Observable
import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract
import mvp.ljb.kt.contract.IModelContract

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/02/22
 * @Description input description
 **/
interface WeChatCodeMainContract {

    interface IView : ICommView {
        fun onTabListSuccess(tabBean: WCodeTabBean)
    }

    interface IPresenter : IPresenterContract {
        fun getTabList()
    }

    interface IModel : IModelContract {
        fun getTabList():Observable<WCodeTabBean>
    }
}
