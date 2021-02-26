package com.ljb.android.component.project.contract

import com.ljb.android.comm.mvp.ICommView
import com.ljb.android.component.project.bean.ProjectListBean
import io.reactivex.rxjava3.core.Observable
import mvp.ljb.kt.contract.IModelContract
import mvp.ljb.kt.contract.IPresenterContract

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/02/26
 * @Description input description
 **/
interface ProjectListContract {

    interface IView : ICommView {
        fun onProjectListSuccess(bean: ProjectListBean)
        fun onProjectListError()
    }

    interface IPresenter : IPresenterContract {
        fun getProjectList(id: String, page: Int)
    }

    interface IModel : IModelContract {
        fun getProjectList(id: String, page: Int):Observable<ProjectListBean>
    }
}
