package com.ljb.android.component.project.presenter

import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.ljb.android.component.project.contract.ProjectMainContract
import com.ljb.android.component.project.model.ProjectMainModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/02/26
 * @Description input description
 **/
class ProjectMainPresenter : BaseMvpPresenter<ProjectMainContract.IView, ProjectMainContract.IModel>(), ProjectMainContract.IPresenter{

    override fun registerModel() = ProjectMainModel::class.java

}
