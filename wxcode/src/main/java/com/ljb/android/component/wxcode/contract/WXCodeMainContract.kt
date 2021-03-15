package com.ljb.android.component.wxcode.contract

import com.ljb.android.comm.mvp.ICommView
import com.ljb.android.component.wxcode.bean.WXCodeTabBean
import io.reactivex.rxjava3.core.Observable
import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IModelContract

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/02/22
 * @Description input description
 **/
interface WXCodeMainContract {

    interface IView : ICommView {
        fun onTabListSuccess(tabBean: WXCodeTabBean)
    }

    interface IPresenter : IPresenterContract {
        fun getTabList()
    }

    interface IModel : IModelContract {
        fun getTabList():Observable<WXCodeTabBean>
    }
}
