package com.ljb.android.component.project.contract

import com.ljb.android.comm.mvp.ICommView
import com.ljb.android.component.project.bean.ProjectTabBean
import io.reactivex.rxjava3.core.Observable
import mvp.ljb.kt.contract.IModelContract
import mvp.ljb.kt.contract.IPresenterContract

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/02/26
 * @Description input description
 **/
interface ProjectMainContract {

    interface IView : ICommView {
        fun onTabListSuccess(data: ProjectTabBean)
    }

    interface IPresenter : IPresenterContract {
        fun getTabList()
    }

    interface IModel : IModelContract {
        fun getTabList(): Observable<ProjectTabBean>
    }
}
