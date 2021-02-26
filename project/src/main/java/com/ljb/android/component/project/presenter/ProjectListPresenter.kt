package com.ljb.android.component.project.presenter

import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.ljb.android.component.project.contract.ProjectListContract
import com.ljb.android.component.project.model.ProjectListModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/02/26
 * @Description input description
 **/
class ProjectListPresenter : BaseMvpPresenter<ProjectListContract.IView, ProjectListContract.IModel>(), ProjectListContract.IPresenter{

    override fun registerModel() = ProjectListModel::class.java

}
