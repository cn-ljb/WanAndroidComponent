package com.ljb.android.component.project.presenter

import com.ljb.android.comm.rx.subscribeNet
import com.ljb.android.component.project.contract.ProjectListContract
import com.ljb.android.component.project.model.ProjectListModel
import mvp.ljb.kt.presenter.BaseMvpPresenter
import net.ljb.kt.utils.RxUtils

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/02/26
 * @Description input description
 **/
class ProjectListPresenter :
    BaseMvpPresenter<ProjectListContract.IView, ProjectListContract.IModel>(),
    ProjectListContract.IPresenter {

    override fun registerModel() = ProjectListModel::class.java

    override fun getProjectList(id: String, page: Int) {
        getModel().getProjectList(id, page)
            .compose(RxUtils.schedulerIO2Main())
            .compose(RxUtils.bindToLife(getMvpView()))
            .subscribeNet(getMvpView()) {
                onNextEx {
                    getMvpView().onProjectListSuccess(it)
                }

                onErrorEx {
                    getMvpView().onProjectListError()
                }
            }
    }

}
