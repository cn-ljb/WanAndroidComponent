package com.ljb.android.component.project.presenter

import com.ljb.android.comm.rx.subscribeNet
import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.ljb.android.component.project.contract.ProjectMainContract
import com.ljb.android.component.project.model.ProjectMainModel
import net.ljb.kt.utils.RxUtils

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/02/26
 * @Description input description
 **/
class ProjectMainPresenter :
    BaseMvpPresenter<ProjectMainContract.IView, ProjectMainContract.IModel>(),
    ProjectMainContract.IPresenter {

    override fun registerModel() = ProjectMainModel::class.java

    override fun getTabList() {
        getModel().getTabList()
            .compose(RxUtils.schedulerIO2Main())
            .compose(RxUtils.bindToLife(getMvpView()))
            .subscribeNet(getMvpView(), true) {

                onNextEx {
                    getMvpView().onTabListSuccess(it)
                }

            }
    }

}
